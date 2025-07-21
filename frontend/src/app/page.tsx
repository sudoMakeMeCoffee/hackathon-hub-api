import Link from "next/link";
import Team from "@/components/Team";
import Events from "@/components/Events";
import Testimonials from "@/components/Testimonials";

export default function Home() {
  return (
    <div>
      <div className="relative w-full h-screen overflow-hidden">
        <video
          className="absolute top-0 left-0 w-full h-full object-cover"
          width="1920"
          height="1080"
          autoPlay
          loop
          muted
        >
          <source src="/background.mp4" type="video/mp4" />
        </video>
        <div className="absolute inset-0 left-4 flex flex-col justify-center">
          <div className="text-white text-9xl block">A community for </div>
          <div className="text-white text-9xl block">
            competitive programmers.
          </div>
        </div>
        <div className="absolute top-4 left-4 flex items-center justify-center">
          <div className="text-white text-5xl text-center font-bold">
            Hackathon Hub
          </div>
        </div>
        <div className="absolute top-4 right-4 flex items-center justify-center">
          <Link href="#team" className="text-white text-3xl text-center m-4">
            Team
          </Link>
          <Link href="#events" className="text-white text-3xl text-center m-4">
            Events
          </Link>
          <Link
            href="#testimonials"
            className="text-white text-3xl text-center m-4"
          >
            Testimonials
          </Link>
          <Link href="#contact" className="text-white text-3xl text-center m-4">
            Contact
          </Link>
        </div>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 24 24"
          fill="currentColor"
          className="absolute bottom-4 left-1/2 transform -translate-x-1/2 w-16 h-16 text-white cursor-pointer animate-bounce"
        >
          <path d="M4 3C3.44772 3 3 3.44772 3 4V20C3 20.5523 3.44772 21 4 21H20C20.5523 21 21 20.5523 21 20V4C21 3.44772 20.5523 3 20 3H4ZM11.9996 17.656L6.0498 11.7062H10.9996V6.34229H12.9996V11.7062H17.9493L11.9996 17.656Z"></path>
        </svg>
      </div>
      <Team />
      <Events />
      <Testimonials />
      <div
        id="contact"
        className="flex flex-col bg-black text-white w-full items-start px-4 py-20"
      >
        <div className="text-8xl mb-8">Contact</div>
        <div className="flex gap-30">
          <div>
            <div className="text-4xl">Socials</div>
            <div className="text-2xl m-2">
              <div>Facebook</div>
              <div>Instagram</div>
              <div>Linkedin</div>
            </div>
          </div>
          <div>
            <div className="text-4xl">Connect with us</div>
            <div className="text-2xl m-2">
              <div>hackathonhub@gmail.com</div>
              <div>L2-106, FOC, NSBM.</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
