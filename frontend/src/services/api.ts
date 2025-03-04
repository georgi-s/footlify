import axios from 'axios';
import { Spieler, Mannschaft, Liga, Formation } from '../types';

const API_BASE_URL = 'http://localhost:8080/api';

// API Response Types
export type ApiResponseData<T> = { data: T };
export type ApiResponse<T> = Promise<ApiResponseData<T>>;

const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});



// Spieler API
export const spielerApi = {
    getAll: (): ApiResponse<Spieler[]> => api.get('/spieler'),
    getById: (id: number): ApiResponse<Spieler> => api.get(`/spieler/${id}`),
    create: (spieler: Omit<Spieler, 'id'>): ApiResponse<Spieler> => api.post('/spieler', spieler),
    update: (id: number, spieler: Omit<Spieler, 'id'>): ApiResponse<Spieler> => api.put(`/spieler/${id}`, spieler),
    delete: (id: number): ApiResponse<void> => api.delete(`/spieler/${id}`),
    getByMannschaft: (mannschaftId: number): ApiResponse<Spieler[]> => api.get(`/spieler/mannschaft/${mannschaftId}`),
    getByPosition: (position: string): ApiResponse<Spieler[]> => api.get(`/spieler/position/${position}`),
    transfer: (id: number, mannschaftId: number | undefined): ApiResponse<Spieler> => api.post(`/spieler/${id}/transfer`, { mannschaftId }),
};

// Mannschaft API
export const mannschaftApi = {
    getAll: (): ApiResponse<Mannschaft[]> => api.get('/mannschaften'),
    getById: (id: number): ApiResponse<Mannschaft> => api.get(`/mannschaften/${id}`),
    create: (mannschaft: Omit<Mannschaft, 'id'>): ApiResponse<Mannschaft> => api.post('/mannschaften', mannschaft),
    update: (id: number, mannschaft: Mannschaft): ApiResponse<Mannschaft> => api.put(`/mannschaften/${id}`, mannschaft),
    delete: (id: number): ApiResponse<void> => api.delete(`/mannschaften/${id}`),
    getByLiga: (ligaId: number): ApiResponse<Mannschaft[]> => api.get(`/mannschaften/liga/${ligaId}`),
    checkFormation: (id: number): ApiResponse<{valid: boolean; message: string}> => api.post(`/mannschaften/${id}/formation/check`),
};

// Liga API
export const ligaApi = {
    getAll: () => api.get<Liga[]>('/ligen'),
    getById: (id: number) => api.get<Liga>(`/ligen/${id}`),
    create: (liga: Omit<Liga, 'id'>) => api.post<Liga>('/ligen', liga),
    update: (id: number, liga: Liga) => api.put<Liga>(`/ligen/${id}`, liga),
    delete: (id: number) => api.delete(`/ligen/${id}`),
};

// Formation API
export const formationApi = {
    getAll: () => api.get<Formation[]>('/formationen'),
    getById: (id: number) => api.get<Formation>(`/formationen/${id}`),
    create: (formation: Omit<Formation, 'id'>) => api.post<Formation>('/formationen', formation),
    update: (id: number, formation: Formation) => api.put<Formation>(`/formationen/${id}`, formation),
    delete: (id: number) => api.delete(`/formationen/${id}`),
};
