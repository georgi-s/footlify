// src/services/mannschaftService.ts
import apiClient from "./api";
import { MannschaftDTO } from "../types/MannschaftDTO";

export const getMannschaften = async (): Promise<MannschaftDTO[]> => {
  const response = await apiClient.get<MannschaftDTO[]>("/mannschaften");
  return response.data;
};

export const createMannschaft = async (
  mannschaftData: Partial<MannschaftDTO>
): Promise<MannschaftDTO> => {
  const response = await apiClient.post<MannschaftDTO>(
    "/mannschaften",
    mannschaftData
  );
  return response.data;
};

export const updateMannschaft = async (
  id: number,
  mannschaftData: Partial<MannschaftDTO>
): Promise<MannschaftDTO> => {
  const response = await apiClient.put<MannschaftDTO>(
    `/mannschaften/${id}`,
    mannschaftData
  );
  return response.data;
};

export const deleteMannschaft = async (id: number): Promise<void> => {
  await apiClient.delete(`/mannschaften/${id}`);
};
