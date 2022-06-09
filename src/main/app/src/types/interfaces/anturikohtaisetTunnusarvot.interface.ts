import MittausSuuntaTypeEnum from "../enums/mittausSuuntaType.enum";


// "MITTAUSSUUNTA_XYZ" CHAR CHECK ( MITTAUSSUUNTA_XYZ IN('X', 'Y', 'Z') ),
export interface IAnturikohtaisetTunnusarvo {
  readonly mittaussuunta_xyz: MittausSuuntaTypeEnum
  tarinan_maksimiarvo: number
  hallitseva_taajuus: number
  tarinan_tunnusluku_vw95_rms: number
}
