import React from 'react';
import { Toaster } from 'sonner';

interface LayoutProps {
  children: React.ReactNode;
}

const Layout: React.FC<LayoutProps> = ({ children }) => {
  return (
    <div className="min-h-screen bg-background font-mono antialiased">
      <div className="relative flex min-h-screen flex-col">
        <div className="flex-1">
          <div className="min-h-screen mx-auto max-w-[1400px] px-4 sm:px-6 lg:px-8">
            {children}
          </div>
        </div>
      </div>
      <Toaster richColors position="top-right" />
    </div>
  );
};

export default Layout;
