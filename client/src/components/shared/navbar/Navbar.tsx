"use client";

import { Button } from "@/components/ui/button";
import { SidebarTrigger } from "@/components/ui/sidebar";
import { Theme } from "@/context/theme-provider";
import { useTheme } from "@/hooks/useTheme";
import { cn } from "@/lib/utils";
import { FileText, Monitor, Moon, Sun } from "lucide-react";
import React, { useMemo } from "react";
import styles from "./Navbar.module.css";
import { usePathname } from "next/navigation";

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
  const pathname = usePathname();
  const isRoot = useMemo(() => {
    return pathname === "/";
  }, [pathname]);

  const handleThemeToggle = () => {
    const nextOptions = themeOptions[theme].next;
    setTheme(nextOptions);
  };
  return (
    <header className={cn("sticky top-0 z-50 bg-background mr-4 ml-4")}>
      <div className="py-2 md:py-2 flex justify-between items-center container">
        <div className="flex gap-2 font-bold text-2xl items-center">
          {!isRoot && <SidebarTrigger />}
          <FileText className={styles.icon} size={30} />
          <strong>WordPack</strong>
        </div>
        {isRoot && (
          <div className="md:flex hidden gap-4">
            <Button variant={"link"}>section 1</Button>
            <Button variant={"link"}>section 3</Button>
            <Button variant={"link"}>section 2</Button>
          </div>
        )}
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
