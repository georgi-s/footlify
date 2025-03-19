import apiClient from "../api";

// API Response Typ
export interface ApiResponseData<T> {
  data: T;
  message?: string;
  status?: number;
}

// Mannschaft API
export const mannschaftApi = {
  getAll: async (): Promise<ApiResponseData<any[]>> => {
    const response = await apiClient.get("/mannschaften");
    return { data: response.data };
  },
  
  getById: async (id: number): Promise<ApiResponseData<any>> => {
    const response = await apiClient.get(`/mannschaften/${id}`);
    return { data: response.data };
  },
  
  create: async (mannschaft: any): Promise<ApiResponseData<any>> => {
    const response = await apiClient.post("/mannschaften", mannschaft);
    return { data: response.data };
  },
  
  update: async (id: number, mannschaft: any): Promise<ApiResponseData<any>> => {
    const response = await apiClient.put(`/mannschaften/${id}`, mannschaft);
    return { data: response.data };
  },
  
  delete: async (id: number): Promise<ApiResponseData<void>> => {
    const response = await apiClient.delete(`/mannschaften/${id}`);
    return { data: response.data };
  },
  
  checkFormation: async (id: number): Promise<ApiResponseData<{valid: boolean; message: string}>> => {
    // Simuliere API-Antwort, da der Endpunkt vielleicht noch nicht existiert
    return { 
      data: { 
        valid: true, 
        message: "Formation ist gültig" 
      } 
    };
  }
};

// Spieler API
export const spielerApi = {
  getAll: async (): Promise<ApiResponseData<any[]>> => {
    const response = await apiClient.get("/spieler");
    return { data: response.data };
  },
  
  getById: async (id: number): Promise<ApiResponseData<any>> => {
    const response = await apiClient.get(`/spieler/${id}`);
    return { data: response.data };
  },
  
  create: async (spieler: any): Promise<ApiResponseData<any>> => {
    const response = await apiClient.post("/spieler", spieler);
    return { data: response.data };
  },
  
  update: async (id: number, spieler: any): Promise<ApiResponseData<any>> => {
    const response = await apiClient.put(`/spieler/${id}`, spieler);
    return { data: response.data };
  },
  
  delete: async (id: number): Promise<ApiResponseData<void>> => {
    const response = await apiClient.delete(`/spieler/${id}`);
    return { data: response.data };
  },
  
  transfer: async (id: number, mannschaftId?: number): Promise<ApiResponseData<any>> => {
    // Simuliere API-Antwort, da der Endpunkt vielleicht noch nicht existiert
    return { data: { id, mannschaftId } };
  }
};
