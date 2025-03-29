"use client";

import { Button } from "@/components/ui/button";
import { Theme } from "@/context/theme-provider";
import { useTheme } from "@/hooks/useTheme";
import { FileText, Monitor, Moon, Sun } from "lucide-react";
import React from "react";
import styles from "./Navbar.module.css";
import { cn } from "@/lib/utils";

const themeOptions: Record<Theme, { icon: React.ReactNode; next: Theme }> = {
  dark: {
    icon: <Sun size={30} />,
    next: "light",
  },
  light: {
    icon: <Moon size={40} />,
    next: "system",
  },
  system: {
    icon: <Monitor size={30} />,
    next: "dark",
  },
};
export const Navbar = () => {
  const { setTheme, theme } = useTheme();

  const handleThemeToggle = () => {
    const nextOptions = themeOptions[theme].next;
    setTheme(nextOptions);
  };
  return (
    <header className={cn("sticky top-0 z-50 bg-background")}>
      <div className="py-2 md:py-4 flex justify-between items-center container">
        <div className="flex gap-2 font-bold text-2xl items-center">
          <FileText className={styles.icon} size={30} />
          <strong>WordPack</strong>
        </div>
        <div className="md:flex hidden gap-4">
          <Button variant={"link"}>section 1</Button>
          <Button variant={"link"}>section 3</Button>
          <Button variant={"link"}>section 2</Button>
        </div>
        <div>
          <div className="flex gap-2 items-center">
            <Button
              variant="ghost"
              onClick={handleThemeToggle}
              size="lg"
              className={styles.toggleTheme}
            >
              {themeOptions[theme].icon}
            </Button>
            <Button size={"sm"} className="accentBackground">
              Log in
            </Button>
          </div>
        </div>
      </div>
    </header>
  );
};
