export interface Player {
  id: number
  vorname: string
  nachname: string
  position: string
  bewertung: number
  alter: number
  gehalt: number
  trikotnummer: number
  mannschaftId?: number
}

export interface Team {
  id: number
  name: string
  trainer: string
  formation: string
  liga: string
  spieler: Player[]
}
