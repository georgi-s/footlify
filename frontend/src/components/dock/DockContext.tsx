"use client";

import { createContext, useContext } from "react";

type DockApi = {
  hovered: boolean;
  width: number;
  activeIndex: number | null;
  setActiveIndex: (index: number | null) => void;
  zoomLevel: any;
  setIsZooming: (isZooming: boolean) => void;
};

export const DockContext = createContext<DockApi>({
  width: 0,
  hovered: false,
  activeIndex: null,
  setActiveIndex: () => {},
  zoomLevel: null,
  setIsZooming: () => {},
});

export const useDock = () => {
  return useContext(DockContext);
};
