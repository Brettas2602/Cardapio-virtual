import { UserProvider } from "@/contexts/userContext";
import "./globals.css";

export default function RootLayout({ children }) {
  return (
    <html lang="pt" className="scroll-smooth">
      <UserProvider>
        <body className="bg-gray-950">
          {children}
        </body>
      </UserProvider>
    </html>
  );
}
