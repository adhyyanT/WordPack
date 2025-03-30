import { Button } from "@/components/ui/button";
import { cn } from "@/lib/utils";
import { ArrowRight } from "lucide-react";
import Image from "next/image";
import styles from "./Hero.module.css";
import Link from "next/link";

export default function Hero() {
  return (
    <section
      className={cn(
        "text-center pt-12 md:pt-16 flex flex-col container",
        styles.headerContainer
      )}
    >
      <div className={cn("mb-10", styles.boxedText)}>
        ⭐ Create your word packs in minutes
      </div>
      <div
        className={cn(
          "mb-8 text-4xl font-bold tracking-tight md:text-6xl",
          styles.animatedLine
        )}
      >
        Dive in a <span className={cn(styles.animatedText)}>Word Pack</span> to
        skyrocket your Vocabulary
      </div>
      <div className="mx-auto mb-10 max-w-2xl text-lg text-gray-600 dark:text-gray-300 md:text-xl">
        Our innovative flashcards based learning will enhance your vocabulary to
        ace your competitive exams
      </div>
      <div className="flex flex-col md:flex-row gap-4 justify-center mb-20">
        <Link
          href="/home"
          className={cn(styles.button, "px-6 py-3 accentBackground")}
        >
          <div className="flex gap-3 items-center justify-center">
            Create Your WordPack <ArrowRight />
          </div>
        </Link>
        <Button variant={"outline"} className={cn(styles.button, "px-9 py-6")}>
          View Portfolio
        </Button>
      </div>
      <div className="flex justify-center mb-10">
        <Image
          src="/mockup.png"
          height={1024}
          width={800}
          className={styles.image}
          alt="Picture of the author"
        />
      </div>
    </section>
  );
}
