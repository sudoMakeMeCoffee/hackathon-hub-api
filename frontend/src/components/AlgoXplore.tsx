"use client";
import { useState, useEffect } from "react";
import Image from "next/image";

const AlgoXplore = () => {
  const tabs = [
    "ALGXPLR",
    "TIMELINE",
    "REGISTRATION",
    "ABOUT_US",
    "SPONSORS",
    "FAQ",
  ];
  const [activeTab, setActiveTab] = useState(tabs[0]);

  const eventDate = new Date("2024-11-19T00:00:00Z");

  const [timeLeft, setTimeLeft] = useState(() => {
    const now = new Date();
    return Math.max(
      0,
      Math.floor((now.getTime() - eventDate.getTime()) / 1000)
    );
  });

  useEffect(() => {
    const interval = setInterval(() => {
      const now = new Date();
      setTimeLeft(
        Math.max(0, Math.floor((now.getTime() - eventDate.getTime()) / 1000))
      );
    }, 1000);

    return () => clearInterval(interval);
  }, []);

  const formatTime = (time: number) => {
    const days = Math.floor(time / (24 * 3600));
    const hours = Math.floor((time % (24 * 3600)) / 3600);
    const minutes = Math.floor((time % 3600) / 60);
    const seconds = time % 60;

    return (
      <div className="text-center">
        <div>
          <span>{String(days).padStart(2, "0")}</span>:
          <span>{String(hours).padStart(2, "0")}</span>:
          <span>{String(minutes).padStart(2, "0")}</span>:
          <span>{String(seconds).padStart(2, "0")}</span>
        </div>
      </div>
    );
  };

  return (
    <div
      id="algoxplore"
      className="relative bg-white min-h-screen h-screen p-20"
    >
      <Image
        src="/algoxplore.webp"
        alt="AlgoXplore"
        width={1080}
        height={1080}
        className="absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2 brightness-0 opacity-10"
      />
      <div className="relative border-4 border-black w-full h-full">
        <div className="p-8 text-5xl">AlgoXplore 1.0</div>
        <div className="absolute top-40 left-10">
          {tabs.map((tab) => (
            <div
              key={tab}
              className={`text-3xl p-2 cursor-pointer ${
                activeTab === tab ? "bg-black text-white" : ""
              }`}
              onClick={() => setActiveTab(tab)}
            >
              {tab}
            </div>
          ))}
        </div>
        <div className="absolute right-30 top-1/2 -translate-y-1/2 border-4 border-black w-3/4 h-3/4">
          {activeTab === "ALGXPLR" && (
            <div className="p-8">
              <div>
                <div className="mt-10">
                  <h1 className="text-5xl sm:text-7xl xl:text-8xl font-semibold">
                    {formatTime(timeLeft)}
                  </h1>
                  <div className="text-center text-xl mt-2">
                    since Nov 19, 2024
                  </div>
                </div>
                <div className="flex flex-row justify-center mb-16 xl:gap-x-14 text-lg sm:text-2xl sm:gap-x-20 xl:text-3xl mt-1 xl:mt-2 lg:mb-10">
                  <span>DAYS</span>
                  <span>HRS</span>
                  <span>MIN</span>
                  <span>SEC</span>
                </div>
              </div>
              <div className="text-2xl">
                AlgoXplore 1.0, organized by Hackathon Hub NSBM, is an exciting
                event that combines an Algothon and a Capture the Flag (CTF)
                challenge. Taking place at NSBM Green University, the event
                features two competitive rounds designed to test participants
                algorithmic problem-solving and cybersecurity skills. With a
                focus on innovation and teamwork, AlgoXplore 1.0 promises an
                engaging experience for all coding enthusiasts.{" "}
              </div>
            </div>
          )}
          {activeTab === "TIMELINE" && (
            <div className="p-8">Timeline content goes here.</div>
          )}
          {activeTab === "REGISTRATION" && (
            <div className="p-8">Registration form coming soon.</div>
          )}
          {activeTab === "SPONSORS" && (
            <div className="p-8">Our Sponsors will be listed here.</div>
          )}
          {activeTab === "ABOUT_US" && (
            <div className="p-8">About Us: Learn more about the team.</div>
          )}
          {activeTab === "FAQ" && (
            <div className="p-8">Frequently Asked Questions.</div>
          )}
        </div>
      </div>
    </div>
  );
};

export default AlgoXplore;
