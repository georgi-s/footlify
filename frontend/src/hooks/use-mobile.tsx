"use client";

import { useState, useEffect } from "react";

// Definieren Sie Gerätegrößen-Konstanten
export const DEVICE_SIZES = {
  SMALL_PHONE: 375, // iPhone 13 Mini und ähnliche kleine Geräte
  PHONE: 640, // Standard-Mobiltelefone
  TABLET: 768, // Tablets
  DESKTOP: 1024, // Desktop und größere Geräte
};

export function useIsMobile(breakpoint = DEVICE_SIZES.PHONE): boolean {
  const [isMobile, setIsMobile] = useState(false);

  useEffect(() => {
    // Initialer Check
    const checkIfMobile = () => {
      setIsMobile(window.innerWidth < breakpoint);
    };

    // Führe den Check sofort aus
    checkIfMobile();

    // Event-Listener für Größenänderungen
    window.addEventListener("resize", checkIfMobile);

    // Cleanup
    return () => {
      window.removeEventListener("resize", checkIfMobile);
    };
  }, [breakpoint]);

  return isMobile;
}

// Neue Funktion, um spezifische Gerätegrößen zu erkennen
export function useDeviceSize() {
  const [deviceSize, setDeviceSize] = useState({
    isSmallPhone: false, // iPhone 13 Mini und ähnliche
    isPhone: false, // Größere Smartphones
    isTablet: false, // Tablets
    isDesktop: false, // Desktop
  });

  useEffect(() => {
    const checkDeviceSize = () => {
      const width = window.innerWidth;
      setDeviceSize({
        isSmallPhone: width <= DEVICE_SIZES.SMALL_PHONE,
        isPhone:
          width > DEVICE_SIZES.SMALL_PHONE && width <= DEVICE_SIZES.PHONE,
        isTablet: width > DEVICE_SIZES.PHONE && width <= DEVICE_SIZES.TABLET,
        isDesktop: width > DEVICE_SIZES.TABLET,
      });
    };

    checkDeviceSize();
    window.addEventListener("resize", checkDeviceSize);

    return () => {
      window.removeEventListener("resize", checkDeviceSize);
    };
  }, []);

  return deviceSize;
}
