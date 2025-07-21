"use client";

import { useState } from "react";
import Image from "next/image";
const Testimonials = () => {
  const [currentTestimonial, setCurrentTestimonial] = useState(1);
  const [isShown, setIsShown] = useState(false);
  const testimonials = [
    {
      name: "Nipuni Ariyathilaka",
      feedback:
        "The IEEE family has always been a place of constant learning and joy, joining IEEE was a big turning point in my life as this laid the foundation for my future. The person's I met during my tenure have been a constant support to me even after graduating. I wish the student branch all the best and to keep educating the young undergraduates throughout their academics and extra curricular.",
      image: null,
    },
    {
      name: "Wasana Fernando",
      feedback:
        "Being a part of the IEEE community has been an enriching experience. The knowledge and skills I've gained here are invaluable.",
      image: "/wasana.webp",
    },
    {
      name: "Sanidula Liyanage",
      feedback:
        "The opportunities for personal and professional growth within the IEEE community are unmatched. I'm grateful for the experiences I've had.",
      image: null,
    },
    {
      name: "Prabath Selvam",
      feedback:
        "Being a part of the IEEE community has been a transformative experience for me. The skills and connections I've gained are invaluable.",
      image: null,
    },
    {
      name: "Ranudi Kariyapperuma",
      feedback:
        "Being a part of the IEEE community has been a transformative experience for me. The skills and connections I've gained are invaluable.",
      image: null,
    },
    {
      name: "Oneli Premarathne",
      feedback:
        "Being a part of the IEEE community has been a transformative experience for me. The skills and connections I've gained are invaluable.",
      image: null,
    },
  ];
  return (
    <div id="testimonials" className="bg-white px-4 py-20 text-white">
      <div className="text-black text-8xl mb-4">Testimonials</div>
      <div className="text-black text-4xl mb-2 max-w-3xl">
        Hackathon Hub is all about people and their stories.
      </div>
      <div className="text-black text-4xl mb-4 max-w-3xl">
        These are some of them.
      </div>
      <div className="flex !h-[600px] gap-4 overflow-hidden">
        {testimonials.map((testimonial, index) =>
          currentTestimonial === index ? (
            <div
              key={index}
              className="relative bg-black w-4xl flex flex-col items-baseline justify-between"
            >
              <div className="relative w-full h-full flex items-center justify-center overflow-hidden">
                {testimonial.image && (
                  <>
                    <Image
                      src={testimonial.image}
                      alt={testimonial.name}
                      fill
                      className="object-cover"
                    />
                    <div
                      className="absolute inset-0"
                      style={{
                        boxShadow: "inset 0 0 200px 50px rgba(0,0,0,0.6)",
                      }}
                    ></div>
                  </>
                )}
              </div>
              <div className="absolute top-4 left-4 text-6xl">
                {testimonials[currentTestimonial].name}
              </div>
              <div className="absolute bottom-4 left-4 text-3xl">
                {testimonials[currentTestimonial].feedback}
              </div>
            </div>
          ) : (
            <div
              key={index}
              className="bg-black w-40 cursor-pointer hover:scale-105 transition-all duration-300"
              onClick={() => {
                setCurrentTestimonial(index);
                setIsShown(!isShown);
              }}
            >
              <div className="flex items-center justify-center text-5xl text-center rotate-90 h-full w-full whitespace-nowrap">
                {testimonial.name}
              </div>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Testimonials;
