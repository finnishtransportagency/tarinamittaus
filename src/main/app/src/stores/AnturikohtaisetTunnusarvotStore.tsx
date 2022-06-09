import { observable } from "mobx";
import MittausSuuntaTypeEnum from "../types/enums/mittausSuuntaType.enum";
import { IAnturikohtaisetTunnusarvo } from "../types/interfaces/anturikohtaisetTunnusarvot.interface";

// export default class AnturikohtaisetTunnusarvotStore implements IAnturikohtaisetTunnusarvotArray {
//   0: {
//     mittaussuunta_xyz = MittausSuuntaTypeEnum.X;
//     tarinan_maksimiarvo = 0;
//     hallitseva_taajuus = 0;
//     tarinan_tunnusluku_vw95_rms = 0;
//   }
//   1: {
//     mittaussuunta_xyz = MittausSuuntaTypeEnum.Y;
//     tarinan_maksimiarvo = 0;
//     hallitseva_taajuus = 0;
//     tarinan_tunnusluku_vw95_rms = 0;
//   },
//   2:{
//     mittaussuunta_xyz = MittausSuuntaTypeEnum.Z;
//     tarinan_maksimiarvo = 0;
//     hallitseva_taajuus = 0;
//     tarinan_tunnusluku_vw95_rms = 0;
//   }
// }

// class MyInterface implements IAnturikohtaisetTunnusarvotArray {
//   [{
//     mittaussuunta_xyz: MittausSuuntaTypeEnum.X,
//     tarinan_maksimiarvo: 0,
//     hallitseva_taajuus: 0,
//     tarinan_tunnusluku_vw95_rms: 0,
//   },
//   {
//     mittaussuunta_xyz: MittausSuuntaTypeEnum.Y,
//     tarinan_maksimiarvo: 0,
//     hallitseva_taajuus: 0,
//     tarinan_tunnusluku_vw95_rms: 0,
//   },
//   {
//     mittaussuunta_xyz: MittausSuuntaTypeEnum.Z,
//     tarinan_maksimiarvo: 0,
//     hallitseva_taajuus: 0,
//     tarinan_tunnusluku_vw95_rms: 0,
//   }]

//  }
