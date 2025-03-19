import React from "react";
import { useGesture } from "@use-gesture/react";

import { useDock } from "./DockContext";
import { DOCK_ZOOM_LIMIT } from "./Dock";
import { useIsMobile } from "../../hooks/use-mobile";

export const DockDivider = () => {
  const { zoomLevel, setIsZooming, activeIndex } = useDock();
  const isMobile = useIsMobile();

  const bind = useGesture(
    {
      onDrag: ({ down, offset: [_ox, oy], cancel, direction: [_dx, dy] }) => {
        /**
         * Stop the drag gesture if the user goes out of bounds otherwise
         * the animation feels stuck but it's the gesture state catching
         * up to a point where the scaling can actualy animate again.
         */
        if (oy <= DOCK_ZOOM_LIMIT[0] && dy === -1) {
          cancel();
        } else if (oy >= DOCK_ZOOM_LIMIT[1] && dy === 1) {
          cancel();
        } else if (zoomLevel) {
          zoomLevel.start(oy, {
            immediate: down,
          });
        }
      },
      onDragStart: () => {
        setIsZooming(true);
      },
      onDragEnd: () => {
        setIsZooming(false);
      },
    },
    {
      drag: {
        axis: "y",
      },
    }
  );

  if (!zoomLevel) {
    return null;
  }

  // Verwende die App-Farbpalette für den Divider, wenn eine Karte aktiv ist
  const isActive = activeIndex !== null;

  return (
    <div
      className="flex items-center justify-center h-full px-1 sm:px-2"
      {...bind()}
    >
      <span
        className={`h-6 sm:h-8 w-0.5 rounded-full transition-colors duration-300 ${
          isActive
            ? "bg-blue-400/40 dark:bg-blue-500/40"
            : "bg-white/30 dark:bg-gray-700/30"
        }`}
      />
    </div>
  );
};
