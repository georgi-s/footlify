"use client";

import * as React from "react";
import {
  animated,
  useSpring,
  useIsomorphicLayoutEffect,
} from "@react-spring/web";
import { useMousePosition } from "../../hooks/useMousePosition";
import { useWindowResize } from "../../hooks/useWindowResize";
import { useDock } from "./DockContext";
// Importieren Sie die neue useDeviceSize-Funktion
import { useIsMobile, useDeviceSize } from "../../hooks/use-mobile";

interface DockCardProps {
  children: React.ReactNode;
  onClick?: () => void;
  index?: number;
}

// Angepasste Größen für verschiedene Geräte
const SIZES = {
  // Desktop
  DESKTOP: {
    INITIAL: 48,
    MAX: 76,
  },
  // Standard-Mobiltelefone
  PHONE: {
    INITIAL: 40,
    MAX: 56,
  },
  // Kleine Mobiltelefone (iPhone 13 Mini)
  SMALL_PHONE: {
    INITIAL: 34, // Kleinere Standardgröße für iPhone 13 Mini
    MAX: 48, // Kleinere maximale Größe für iPhone 13 Mini
  },
};

export const DockCard = ({ children, onClick, index }: DockCardProps) => {
  const cardRef = React.useRef<HTMLButtonElement>(null!);
  const [elCenterX, setElCenterX] = React.useState<number>(0);

  const dock = useDock();
  const mousePos = useMousePosition();
  const isMobile = useIsMobile();
  const deviceSize = useDeviceSize();
  const isActive = index !== undefined && dock.activeIndex === index;

  // Wähle die richtigen Größen basierend auf der Gerätegröße
  const getCardSizes = () => {
    if (deviceSize.isSmallPhone) {
      return SIZES.SMALL_PHONE;
    } else if (deviceSize.isPhone || isMobile) {
      return SIZES.PHONE;
    } else {
      return SIZES.DESKTOP;
    }
  };

  const cardSizes = getCardSizes();

  // Berechne die Distanz zwischen Mauszeiger und Kartenmitte
  const distanceFromMouseToCenter = React.useMemo(() => {
    if (!mousePos.x) return 0;
    return Math.abs(mousePos.x - elCenterX);
  }, [mousePos, elCenterX]);

  // Berechne den Skalierungsfaktor basierend auf der Distanz
  const distanceScale = React.useMemo(() => {
    if (!dock.hovered) return 1;
    const distance = distanceFromMouseToCenter;
    const maxDistance = dock.width / 2;
    const scale = Math.cos((distance / maxDistance) * (Math.PI / 2));
    return scale ** 8; // Exponentieller Faktor für stärkeren Effekt
  }, [dock.hovered, dock.width, distanceFromMouseToCenter]);

  // Spring Animation für Größe und Position
  const [springs, api] = useSpring(() => ({
    from: {
      width: cardSizes.INITIAL,
      height: cardSizes.INITIAL,
      y: 0,
    },
    config: {
      mass: 0.6,
      tension: 400,
      friction: 30,
    },
  }));

  // Aktualisiere die Animation
  React.useEffect(() => {
    const initialSize = cardSizes.INITIAL;
    const maxSize = cardSizes.MAX;
    const width = initialSize + (maxSize - initialSize) * distanceScale;
    api.start({
      width: isActive ? maxSize : width,
      height: isActive ? maxSize : width,
      y: dock.hovered || isActive ? -8 * distanceScale : 0,
      immediate: false,
    });
  }, [distanceScale, dock.hovered, api, isActive, cardSizes]);

  useWindowResize(() => {
    if (cardRef.current) {
      const { x, width } = cardRef.current.getBoundingClientRect();
      setElCenterX(x + width / 2);
    }
  });

  useIsomorphicLayoutEffect(() => {
    if (cardRef.current) {
      const { x, width } = cardRef.current.getBoundingClientRect();
      setElCenterX(x + width / 2);
    }
  }, []);

  const handleClick = () => {
    if (index !== undefined) {
      dock.setActiveIndex(index);
    }
    if (onClick) {
      onClick();
    }
  };

  return (
    <div className="flex flex-col items-center">
      <animated.button
        ref={cardRef}
        onClick={handleClick}
        style={{
          ...springs,
          willChange: "transform",
        }}
        className={`relative flex items-center justify-center rounded-2xl backdrop-blur-lg border shadow-lg transition-colors duration-200 ${
          isActive
            ? "bg-blue-500/20 dark:bg-blue-600/30 border-blue-400/50 dark:border-blue-500/40"
            : "bg-white/10 dark:bg-white/5 border-white/20 hover:border-white/30"
        }`}
      >
        <div
          className={`absolute inset-0 rounded-2xl ${
            isActive
              ? "bg-gradient-to-br from-blue-400/30 to-indigo-500/20"
              : "bg-gradient-to-br from-white/20 to-white/5"
          }`}
        />
        <div
          className={`relative z-10 ${
            isActive ? "text-blue-600 dark:text-blue-400" : ""
          }`}
        >
          {children}
        </div>
      </animated.button>

      {/* Indikator unter der aktiven Karte */}
      <div
        className={`h-1.5 w-1.5 rounded-full mt-1.5 transition-all duration-300 ${
          isActive ? "bg-blue-500 dark:bg-blue-400" : "bg-transparent"
        }`}
      />
    </div>
  );
};
