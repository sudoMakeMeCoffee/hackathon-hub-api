"use client";

import { useState, useEffect } from "react";
import axios from "axios";
import * as z from "zod";
import { useForm, SubmitHandler } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";

const eventSchema = z.object({
  id: z.string().optional(),
  name: z.string().min(2).max(24),
  date: z.string().min(2).max(24).optional(),
  description: z.string().min(8).max(48),
  time: z.string().min(2).max(24).optional(),
  location: z.string().min(2).max(24).optional(),
  registerLink: z.url().optional(),
});

type Event = z.infer<typeof eventSchema>;

const Events = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [events, setEvents] = useState<Event[]>([]);
  const [isEditing, setIsediting] = useState(false);
  const [selectedEvent, setSelectedEvent] = useState<string>();

  useEffect(() => {
    readEvents();
  }, []);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Event>({ resolver: zodResolver(eventSchema) });

  const onSubmit: SubmitHandler<Event> = (data) => {
    !isEditing && console.log(data);
    !isEditing && updateEvent(data);
  };

  const readEvents = async () => {
    try {
      setIsLoading(true);
      const response = await axios.get(`${process.env.NEXT_PUBLIC_API}event`);
      setEvents(response.data.data);
    } catch (e) {
      console.log(e);
    } finally {
      setIsLoading(false);
    }
  };

  const updateEvent = async (data: Event) => {
    try {
      const response = await axios.put(
        `${process.env.NEXT_PUBLIC_API}event/${selectedEvent}`,
        data,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );

      await readEvents();
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <div>
      {isLoading ? (
        <div className="animate-pulse text-2xl">loading...</div>
      ) : (
        <form onSubmit={handleSubmit(onSubmit)}>
          {events.map((event) => (
            <div
              key={event.id}
              onClick={() => setSelectedEvent(event.id)}
              className="relative"
            >
              {isEditing && selectedEvent === event.id ? (
                <input
                  {...register("name")}
                  defaultValue={event.name}
                  className={`${errors.name && "text-red-500"}`}
                />
              ) : (
                <div>{event.name}</div>
              )}
              {isEditing && selectedEvent === event.id ? (
                <input
                  {...register("date")}
                  defaultValue={event.date}
                  className={`${errors.date && "text-red-500"}`}
                />
              ) : (
                <div>{event.date}</div>
              )}
              {isEditing && selectedEvent === event.id ? (
                <input
                  {...register("description")}
                  defaultValue={event.description}
                  className={`${errors.description && "text-red-500"}`}
                />
              ) : (
                <div>{event.description}</div>
              )}
              {isEditing && selectedEvent === event.id ? (
                <input
                  {...register("time")}
                  defaultValue={event.time}
                  className={`${errors.time && "text-red-500"}`}
                />
              ) : (
                <div>{event.time}</div>
              )}
              {isEditing && selectedEvent === event.id ? (
                <input
                  {...register("location")}
                  defaultValue={event.location}
                  className={`${errors.location && "text-red-500"}`}
                />
              ) : (
                <div>{event.location}</div>
              )}
              {isEditing && selectedEvent === event.id ? (
                <input
                  {...register("registerLink")}
                  defaultValue={event.registerLink}
                  className={`${errors.registerLink && "text-red-500"}`}
                />
              ) : (
                <div>{event.registerLink}</div>
              )}
              <button type="submit">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 24 24"
                  fill="currentColor"
                  onClick={() => setIsediting(!isEditing)}
                  className="h-12 w-12"
                >
                  <path
                    d={
                      isEditing
                        ? "M4 3H20C20.5523 3 21 3.44772 21 4V20C21 20.5523 20.5523 21 20 21H4C3.44772 21 3 20.5523 3 20V4C3 3.44772 3.44772 3 4 3ZM11.0026 16L18.0737 8.92893L16.6595 7.51472L11.0026 13.1716L8.17421 10.3431L6.75999 11.7574L11.0026 16Z"
                        : "M16.7574 2.99678L9.29145 10.4627L9.29886 14.7099L13.537 14.7024L21 7.23943V19.9968C21 20.5491 20.5523 20.9968 20 20.9968H4C3.44772 20.9968 3 20.5491 3 19.9968V3.99678C3 3.4445 3.44772 2.99678 4 2.99678H16.7574ZM20.4853 2.09729L21.8995 3.5115L12.7071 12.7039L11.2954 12.7064L11.2929 11.2897L20.4853 2.09729Z"
                    }
                  ></path>
                </svg>
              </button>
            </div>
          ))}
        </form>
      )}
    </div>
  );
};

export default Events;
