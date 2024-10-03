import SeliteTypeEnum from "../types/enums/seliteType.enum";
import IAsennuspaikantyyppi from "../types/interfaces/asennuspaikanTyyppi.interface";


export default class AsennuspaikanTyyppi implements IAsennuspaikantyyppi {
  selite = SeliteTypeEnum.muu;
  lisatiedot = '';
}