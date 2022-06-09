import { IAnturikohtaisetTunnusarvo } from "./anturikohtaisetTunnusarvot.interface";
import IAsennuspaikantyyppi from "./asennuspaikanTyyppi.interface";


export default interface IAsennettuAnturi {
  malli: string
  gps_lat: number
  gps_long: number
  etaisyys_radasta_jos_eri: number
  kerros: number
  sijoituspaikan_lisaselite: string
  asennuspaikanTyyppi: IAsennuspaikantyyppi
  anturikohtaisetTunnusarvot: IAnturikohtaisetTunnusarvo[]
}
