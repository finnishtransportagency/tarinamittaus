import SeliteTypeEnum from "../types/enums/seliteType.enum";
import IAsennuspaikantyyppi from "../types/interfaces/asennuspaikanTyyppi.interface";


export default class AsennuspaikanTyyppiStore implements IAsennuspaikantyyppi {
  selite = SeliteTypeEnum.muu;
  lisatiedot = '';
}