"use client";

import * as React from "react";
import { useSpringValue } from "@react-spring/web";

import { useWindowResize } from "../../hooks/useWindowResize";
import { DockContext } from "./DockContext";
import { useDeviceSize } from "../../hooks/use-mobile";

// Definiere die Zoom-Limits für den DockDivider
export const DOCK_ZOOM_LIMIT = [-100, 100];

// Angepasste Dock-Breiten für verschiedene Geräte
const DOCK_WIDTH = {
  SMALL_PHONE: "90vw", // Schmaler für iPhone 13 Mini
  PHONE: "95vw", // Standard für Mobiltelefone
  TABLET: "auto", // Auto für Tablets und größer
  DESKTOP: "auto", // Auto für Desktop
};

interface DockProps {
  children: React.ReactNode;
  initialActiveIndex?: number | null;
}

export const Dock = ({ children, initialActiveIndex = null }: DockProps) => {
  const [hovered, setHovered] = React.useState(false);
  const [width, setWidth] = React.useState(0);
  const [activeIndex, setActiveIndex] = React.useState<number | null>(
    initialActiveIndex
  );
  const [isZooming, setIsZooming] = React.useState(false);
  const deviceSize = useDeviceSize();

  const zoomLevel = useSpringValue(0, {
    config: {
      mass: 0.6,
      tension: 400,
      friction: 30,
    },
  });
  const dockRef = React.useRef<HTMLDivElement>(null!);

  useWindowResize(() => {
    if (dockRef.current) {
      setWidth(dockRef.current.clientWidth);
    }
  });

  // Bestimme die Dock-Breite basierend auf der Gerätegröße
  const getDockWidth = () => {
    if (deviceSize.isSmallPhone) return DOCK_WIDTH.SMALL_PHONE;
    if (deviceSize.isPhone) return DOCK_WIDTH.PHONE;
    if (deviceSize.isTablet) return DOCK_WIDTH.TABLET;
    return DOCK_WIDTH.DESKTOP;
  };

  // Bestimme den Abstand zwischen den Elementen basierend auf der Gerätegröße
  const getGapSize = () => {
    if (deviceSize.isSmallPhone) return "gap-1";
    if (deviceSize.isPhone) return "gap-2";
    return "gap-4";
  };

  // Bestimme die Padding-Größe basierend auf der Gerätegröße
  const getPaddingSize = () => {
    if (deviceSize.isSmallPhone) return "px-1.5 py-1.5";
    if (deviceSize.isPhone) return "px-2 py-2";
    return "px-4 py-3";
  };

  return (
    <DockContext.Provider
      value={{
        hovered,
        width,
        activeIndex,
        setActiveIndex,
        zoomLevel,
        setIsZooming,
      }}
    >
      <div
        ref={dockRef}
        onMouseOver={() => setHovered(true)}
        onMouseOut={() => setHovered(false)}
        className={`flex items-end ${getGapSize()} ${getPaddingSize()} bg-white/10 dark:bg-black/20 backdrop-blur-2xl rounded-2xl border border-white/20 shadow-xl max-w-fit`}
        style={{
          width: getDockWidth(),
        }}
      >
        {React.Children.map(children, (child, index) => {
          if (React.isValidElement(child)) {
            return React.cloneElement(child, {
              ...child.props,
              index,
            });
          }
          return child;
        })}
      </div>
    </DockContext.Provider>
  );
};
