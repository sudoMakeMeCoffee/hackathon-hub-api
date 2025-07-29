"use client";
import { useState } from "react";
import Users from "./Users";
import Events from "./Events";
import Testimonials from "./Testimonials";
import Tasks from "./Tasks";
import { Inter } from "next/font/google";

const inter = Inter({
  subsets: ["latin"],
});

const NavMenu = () => {
  const tabs = [
    {
      name: "Users",
      icon: "M20 22H4V20C4 17.2386 6.23858 15 9 15H15C17.7614 15 20 17.2386 20 20V22ZM12 13C8.68629 13 6 10.3137 6 7C6 3.68629 8.68629 1 12 1C15.3137 1 18 3.68629 18 7C18 10.3137 15.3137 13 12 13Z",
    },
    {
      name: "Events",
      icon: "M17 3H21C21.5523 3 22 3.44772 22 4V20C22 20.5523 21.5523 21 21 21H3C2.44772 21 2 20.5523 2 20V4C2 3.44772 2.44772 3 3 3H7V1H9V3H15V1H17V3ZM4 9V19H20V9H4ZM6 13H11V17H6V13Z",
    },
    {
      name: "Testimonials",
      icon: "M21 3C21.5523 3 22 3.44772 22 4V18C22 18.5523 21.5523 19 21 19H6.455L2 22.5V4C2 3.44772 2.44772 3 3 3H21ZM10.5153 7.4116C8.72825 8.18684 7.5 9.75543 7.5 11.5052C7.5 12.5 7.77658 13.1137 8.29171 13.6605C8.61598 14.0048 9.12905 14.25 9.66558 14.25C10.6321 14.25 11.4156 13.4665 11.4156 12.5C11.4156 11.5795 10.7045 10.8389 9.80236 10.7553C9.64107 10.7403 9.47827 10.7431 9.32317 10.7645L9.32344 10.6729C9.32873 10.2322 9.4223 8.9333 10.9616 8.1004L10.5153 7.4116ZM15.5153 7.4116C13.7283 8.18684 12.5 9.75543 12.5 11.5052C12.5 12.5 12.7766 13.1137 13.2917 13.6605C13.616 14.0048 14.1291 14.25 14.6656 14.25C15.6321 14.25 16.4156 13.4665 16.4156 12.5C16.4156 11.5795 15.7045 10.8389 14.8024 10.7553C14.6411 10.7403 14.4783 10.7431 14.3232 10.7645L14.3234 10.6729C14.3287 10.2322 14.4223 8.9333 15.9616 8.1004L15.5153 7.4116Z",
    },
    {
      name: "Tasks",
      icon: "M21 3V21.0082C21 21.556 20.5551 22 20.0066 22H3.9934C3.44476 22 3 21.5447 3 21.0082V2.9918C3 2.44405 3.44495 2 3.9934 2H20C20.5523 2 21 2.44772 21 3ZM11.2929 13.1213L8.81802 10.6464L7.40381 12.0607L11.2929 15.9497L16.9497 10.2929L15.5355 8.87868L11.2929 13.1213Z",
    },
  ];

  const [activeTab, setActiveTab] = useState(tabs[3].name);

  return (
    <div className={`${inter.className}`}>
      {tabs.map((tab, index) => (
        <div key={index} onClick={() => setActiveTab(tab.name)}>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="currentColor"
            className="w-6 h-6 m-4"
          >
            <path d={tab.icon}></path>
          </svg>
        </div>
      ))}
      <div>
        {activeTab === "Users" && <Users />}
        {activeTab === "Events" && <Events />}
        {activeTab === "Testimonials" && <Testimonials />}
        {activeTab === "Tasks" && <Tasks />}
      </div>
    </div>
  );
};

export default NavMenu;
