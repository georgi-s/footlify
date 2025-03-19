"use client";

import { useDeviceSize } from "./use-mobile";

export function useIconSize() {
  const deviceSize = useDeviceSize();

  if (deviceSize.isSmallPhone) return 18;
  if (deviceSize.isPhone) return 20;
  return 24;
}
