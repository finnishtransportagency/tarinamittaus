import IAsennettuAnturi from "./asennettuAnturi.interface";


export default interface IMittaus {
  kohde_id: number
  alkuaika: string
  loppuaika: string
  mittaus_asianhallinta_id: string
  pdf_raportin_linkki: string
  rakennuksen_pinta_ala: number
  perustamistapa: string
  julkisivumateriaali: string
  runkomateriaali: string
  rakennusvuosi: number
  katuosoite: string
  postinumero: string
  created_by_lx: string
  asennettuAnturi: IAsennettuAnturi[]
}