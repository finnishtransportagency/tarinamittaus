import { makeObservable, observable } from "mobx";
import MittausSuuntaTypeEnum from "../types/enums/mittausSuuntaType.enum";
import { IAnturikohtaisetTunnusarvo } from "../types/interfaces/anturikohtaisetTunnusarvot.interface";
import IAsennettuAnturi from "../types/interfaces/asennettuAnturi.interface";
import AsennuspaikanTyyppiStore from "./AsennuspaikanTyyppiStore";


export default class AsennettuAnturiStore implements IAsennettuAnturi {
  malli = '';
  gps_lat = 0;
  gps_long = 0;
  etaisyys_radasta_jos_eri = 0;
  kerros = 0;
  sijoituspaikan_lisaselite = '';
  asennuspaikanTyyppi = new AsennuspaikanTyyppiStore();
  anturikohtaisetTunnusarvot = tunnusArvot;

  constructor() {
    makeObservable(this, {
      malli: observable,
      gps_lat: observable,
      gps_long: observable,
      etaisyys_radasta_jos_eri: observable,
      kerros: observable,
      sijoituspaikan_lisaselite: observable,
      asennuspaikanTyyppi: observable,
      anturikohtaisetTunnusarvot: observable
    })
  }
}

let tunnusArvot: IAnturikohtaisetTunnusarvo[] = [
  {
    mittaussuunta_xyz: MittausSuuntaTypeEnum.X,
    tarinan_maksimiarvo: 0,
    hallitseva_taajuus: 0,
    tarinan_tunnusluku_vw95_rms: 0,
  },
  {
    mittaussuunta_xyz: MittausSuuntaTypeEnum.Y,
    tarinan_maksimiarvo: 0,
    hallitseva_taajuus: 0,
    tarinan_tunnusluku_vw95_rms: 0,
  },
  {
    mittaussuunta_xyz: MittausSuuntaTypeEnum.Z,
    tarinan_maksimiarvo: 0,
    hallitseva_taajuus: 0,
    tarinan_tunnusluku_vw95_rms: 0,
  }
];