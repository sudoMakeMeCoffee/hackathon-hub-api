"use client";

import { useState, useEffect } from "react";
import axios from "axios";
import * as z from "zod";
import {
  useForm,
  useFieldArray,
  SubmitHandler,
  Control,
} from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";

const subTaskSchema = z.object({
  id: z.string().optional(),
  title: z.string().min(2).max(24),
  description: z.string().min(8).max(48).optional(),
  creationDate: z.date(),
  deadline: z.date().optional(),
  isCompleted: z.boolean(),
  assignees: z.array(z.string()),
});

const taskSchema = z.object({
  id: z.string().optional(),
  title: z.string().min(2).max(24),
  description: z.string().min(8).max(48).optional(),
  creationDate: z.date(),
  deadline: z.date().optional(),
  isCompleted: z.boolean(),
  assignees: z.array(z.string()),
  subtasks: z.array(subTaskSchema),
});

type Task = z.infer<typeof taskSchema>;

type User = {
  id: string;
  username: string;
  email: string;
  role: "admin" | "editor";
};

const Task = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [isSearching, setIsSearching] = useState(false);
  const [tasks, setTasks] = useState<Task[]>([]);
  const [searchedUsers, setSearchedUsers] = useState<User[]>([]);
  const [selectedUsers, setSelectedUsers] = useState<User[]>([]);
  // const [subTaskSelectedUsers, setSubTaskSelectedUsers] = useState<{
  //   [key: number]: User[];
  // }>({});

  const {
    register,
    handleSubmit,
    watch,
    reset,
    control,
    formState: { errors },
  } = useForm<Task>({
    resolver: zodResolver(taskSchema),
  });

  const { fields, append, remove } = useFieldArray({
    name: "subtasks",
    control,
  });

  const onSubmit: SubmitHandler<Task> = (data) => console.log(data);

  const readTasks = async () => {
    try {
      setIsLoading(true);
      const response = await axios.get(`${process.env.NEXT_PUBLIC_API}task`);
      setTasks(response.data.data);
    } catch (e) {
      console.log(e);
    } finally {
      setIsLoading(false);
    }
  };

  const searchUser = async (query: string) => {
    if (!query || query.trim() === "") {
      setSearchedUsers([]);
      return;
    }

    try {
      setIsSearching(true);
      const response = await axios.get(
        `${process.env.NEXT_PUBLIC_API}user/search`,
        {
          params: {
            q: query,
          },
        }
      );
      setSearchedUsers(response.data.data);
    } catch (e) {
      console.log(e);
      setSearchedUsers([]);
    } finally {
      setIsSearching(false);
    }
  };

  return (
    <div className="w-1/2">
      <form
        onSubmit={handleSubmit(onSubmit)}
        className="flex flex-col gap-2 border-1 border-black"
      >
        <label htmlFor="title">
          Title
          <input
            {...register("title")}
            id="title"
            type="text"
            className="border-1 border-black"
          />
        </label>
        <label htmlFor="description">
          Description
          <input
            {...register("description")}
            id="description"
            type="text"
            className="border-1 border-black"
          />
        </label>
        <label htmlFor="deadline">
          Deadline
          <input
            {...register("deadline")}
            id="deadline"
            type="datetime-local"
            className="border-1 border-black"
          />
        </label>
        <label htmlFor="is_completed">
          Is Completed
          <input
            {...register("isCompleted")}
            id="is_completed"
            type="checkbox"
            className="border-1 border-black"
          />
        </label>
        <div>
          <div>Search</div>
          <input
            type="text"
            onChange={(e) => searchUser(e.target.value)}
            className="border-1 border-black"
          />
          {isSearching && <div>Searching...</div>}
          {!isSearching &&
            searchedUsers.map((user) => (
              <div
                // adds the clicked on user to selectedUsers
                onClick={() => {
                  setSelectedUsers((prev) => [...prev, user]);
                }}
                key={user.id}
              >
                {user.username}
              </div>
            ))}
          {selectedUsers.map((user) => (
            <div
              key={user.id}
              // removes the clicked on user from selectedUsers
              onClick={() => {
                setSelectedUsers(selectedUsers.filter((_) => _.id !== user.id));
              }}
              className="font-semibold"
            >
              {user.username}
            </div>
          ))}
        </div>
        <div
          onClick={() =>
            append({
              title: "",
              description: "",
              creationDate: new Date(),
              deadline: undefined,
              isCompleted: false,
              assignees: [],
            })
          }
          className="border-1 border-black"
        >
          Add subtask
        </div>
        {fields.map((field, index) => (
          <div key={field.id}>
            <div>Subtask {index + 1}</div>

            <label htmlFor={`subtask_${index}_title`}>
              Title
              <input
                {...register(`subtasks.${index}.title`)}
                id={`subtask_${index}_title`}
                type="text"
                className="border-1 border-black"
              />
            </label>

            <label htmlFor={`subtask_${index}_description`}>
              Description
              <input
                {...register(`subtasks.${index}.description`)}
                id={`subtask_${index}_description`}
                type="text"
                className="border-1 border-black"
              />
            </label>

            <label htmlFor={`subtask_${index}_deadline`}>
              Deadline
              <input
                {...register(`subtasks.${index}.deadline`)}
                id={`subtask_${index}_deadline`}
                type="datetime-local"
                className="border-1 border-black"
              />
            </label>

            <label htmlFor={`subtask_${index}_is_completed`}>
              <input
                {...register(`subtasks.${index}.isCompleted`)}
                id={`subtask_${index}_is_completed`}
                type="checkbox"
              />
              Is Completed
            </label>

            <div>
              <label>Assignees for Subtask</label>
              <input
                type="text"
                onChange={(e) => searchUser(e.target.value)}
                className="border-1 border-black"
              />

              {/* still has to figure out a way to assign assingnees to a specific subtask */}
              <div>
                {selectedUsers.map((user) => (
                  <div
                    key={user.id}
                    onClick={() => {
                      const currentAssignees =
                        watch(`subtasks.${index}.assignees`) || [];
                      if (!currentAssignees.includes(user.id)) {
                        register(`subtasks.${index}.assignees`).onChange({
                          target: { value: [...currentAssignees, user.id] },
                        });
                      }
                    }}
                    className="cursor-pointer text-blue-600 hover:underline"
                  >
                    + {user.username}
                  </div>
                ))}
              </div>
            </div>
            <button
              type="button"
              onClick={() => remove(index)}
              className="border-1 border-black"
            >
              Remove Subtask
            </button>
          </div>
        ))}
        <button type="submit" className="border-1 border-black">
          Submit
        </button>
      </form>
    </div>
  );
};

export default Task;
