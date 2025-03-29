import React from "react";
import styles from "./Banner.module.css";
import { cn } from "@/lib/utils";
import { Github, MoveRight, Package, Users } from "lucide-react";
import { Button } from "@/components/ui/button";

// const stats = [
//   {
//     icon: <Github />,
//     text: "0 Github starts",
//   },
//   {
//     icon: <Users />,
//     text: "10 Happy Users",
//   },
//   {
//     icon: <Package />,
//     text: "15 Word Packs Created",
//   },
// ];
const stats = [
  {
    icon: <MoveRight />,
    text: "Start editing public word packs. Go Pro ",
  },
];
export default function Banner() {
  return (
    <div
      className={cn(
        styles.bannerContainer,
        "py-2 flex justify-center alternateBackground"
      )}
    >
      {stats.map((stat, index) => (
        <Button
          variant={"link"}
          key={index}
          className={cn("font-bold md:text-lg", styles.proButton)}
        >
          <p>{stat.text}</p>
          <span>{stat.icon}</span>
        </Button>
      ))}
    </div>
  );
}
