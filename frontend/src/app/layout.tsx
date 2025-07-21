import type { Metadata } from "next";
import { Bebas_Neue } from "next/font/google";
import "./globals.css";
import { Providers } from "./provider";

const figtree = Bebas_Neue({
  subsets: ["latin"],
  weight: "400",
  variable: "--font-figtree",
  display: "swap",
  preload: true,
  fallback: ["sans-serif"],
  style: "normal",
  adjustFontFallback: true,
});

const metadata: Metadata = {
  title: "Hackathon Hub - NSBM",
  description: "",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" className="scroll-smooth">
      <body className={`${figtree.className} antialiased`}>
        <Providers>{children}</Providers>
      </body>
    </html>
  );
}
