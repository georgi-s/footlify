// src/services/spielerService.ts
import apiClient from "./api";
import { SpielerDTO } from "../types/SpielerDTO";

export const getSpieler = async (): Promise<SpielerDTO[]> => {
  const response = await apiClient.get<SpielerDTO[]>("/spieler");
  return response.data;
};

export const createSpieler = async (
  spielerData: Partial<SpielerDTO>
): Promise<SpielerDTO> => {
  const response = await apiClient.post<SpielerDTO>("/spieler", spielerData);
  return response.data;
};

export const updateSpieler = async (
  id: number,
  spielerData: Partial<SpielerDTO>
): Promise<SpielerDTO> => {
  const response = await apiClient.put<SpielerDTO>(
    `/spieler/${id}`,
    spielerData
  );
  return response.data;
};

export const deleteSpieler = async (id: number): Promise<void> => {
  await apiClient.delete(`/spieler/${id}`);
};
