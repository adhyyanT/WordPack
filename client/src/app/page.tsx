import Banner from "@/components/custom/Banner/Banner";
import Hero from "@/components/custom/Hero/Hero";
import { Navbar } from "@/components/shared/navbar/Navbar";
import { ThemeProvider } from "@/context/theme-provider";

export default function Landing() {
  return (
    <>
      <ThemeProvider defaultTheme="system" storageKey="word-pack-theme">
        <Navbar />
      </ThemeProvider>
      <Banner />
      <Hero />
    </>
  );
}
