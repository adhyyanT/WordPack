import { ThemeProviderContext } from "@/context/theme-provider";
import { useContext } from "react";

export function useTheme() {
  const themeContext = useContext(ThemeProviderContext);

  if (!themeContext)
    throw new Error("useTheme must be used within a ThemeProvider");

  return themeContext;
}
