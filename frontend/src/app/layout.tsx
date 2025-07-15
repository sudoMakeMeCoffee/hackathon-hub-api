import type { Metadata } from "next";
import {Open_Sans} from "next/font/google";
import "./globals.css";

const open_Sans = Open_Sans({
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Hackathon Hub - NSBM",
  description: "",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${open_Sans.className} antialiased`}
      >
        {children}
      </body>
    </html>
  );
}
