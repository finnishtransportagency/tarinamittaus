import { makeObservable, observable } from 'mobx';
import IMittaus from "../types/interfaces/mittaus.interface";


export default class MittausStore implements IMittaus {

  alkuaika = '';
  loppuaika = '';
  mittaus_asianhallinta_id = '';
  pdf_raportin_linkki = '';
  rakennuksen_pinta_ala = 0;
  perustamistapa = '';
  julkisivumateriaali = '';
  runkomateriaali = '';
  rakennusvuosi = 0;
  katuosoite = '';
  postinumero = '';
  created_by_lx = '';
  asennettuAnturi = [];

  constructor() {
    makeObservable(this, {
      alkuaika: observable,
      loppuaika: observable,
      mittaus_asianhallinta_id: observable,
      pdf_raportin_linkki: observable,
      rakennuksen_pinta_ala: observable,
      perustamistapa: observable,
      julkisivumateriaali: observable,
      runkomateriaali: observable,
      rakennusvuosi: observable,
      katuosoite: observable,
      postinumero: observable,
      created_by_lx: observable,
      asennettuAnturi: observable
    })
  }
}
