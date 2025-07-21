import Link from "next/link";
import Team from "@/components/Team";
import Events from "@/components/Events";
import Testimonials from "@/components/Testimonials";
import AlgoXplore from "@/components/AlgoXplore";

export default function Home() {
  return (
    <div>
      <div id="home" className="relative w-full h-screen overflow-hidden">
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
          <Link
            href="#algoxplore"
            className="text-white text-3xl text-center m-4"
          >
            AlgoXplore 1.0
          </Link>
        </div>
      </div>
      <Team />
      <Events />
      <Testimonials />
      <AlgoXplore />
      <div
        id="contact"
        className="relative flex flex-col bg-black text-white w-full items-start px-4 py-20"
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
