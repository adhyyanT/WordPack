import { ReactNode } from "react";

export default function HomeLayout({
  children,
}: Readonly<{ children: ReactNode }>) {
  return (
    <div>
      Sidebar
      {children}
    </div>
  );
}
