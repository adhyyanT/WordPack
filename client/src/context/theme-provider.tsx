"use client";
import { createContext, useEffect, useState } from "react";

export type Theme = "dark" | "light" | "system";

type ThemeProviderProps = {
  children: React.ReactNode;
  defaultTheme?: Theme;
  storageKey?: string;
};

type ThemeProviderState = {
  theme: Theme;
  setTheme: (theme: Theme) => void;
};

export const ThemeProviderContext = createContext<ThemeProviderState>({
  setTheme: () => null,
  theme: "system",
});

export function ThemeProvider({
  children,
  defaultTheme = "system",
  storageKey = "vite-ui-theme",
  ...props
}: ThemeProviderProps) {
  const [theme, setTheme] = useState<Theme>(defaultTheme);

  /**
   * Runs only for the first time
   */
  useEffect(() => {
    const preferredTheme = localStorage.getItem(storageKey) ?? defaultTheme;
    if (preferredTheme) {
      setTheme(preferredTheme as Theme);
    }
    console.log("gere", preferredTheme);
  }, []);

  useEffect(() => {
    const root = window.document.documentElement;

    root.classList.remove("light", "dark");

    if (theme === "system") {
      const systemTheme = window.matchMedia("(prefers-color-scheme: dark)")
        .matches
        ? "dark"
        : "light";

      root.classList.add(systemTheme);
      console.log(systemTheme);
      return;
    }

    root.classList.add(theme);
  }, [theme]);

  const value: ThemeProviderState = {
    setTheme: (theme) => {
      localStorage.setItem(storageKey, theme);
      setTheme(theme);
    },
    theme,
  };
  return (
    <ThemeProviderContext.Provider value={value} {...props}>
      {children}
    </ThemeProviderContext.Provider>
  );
}
