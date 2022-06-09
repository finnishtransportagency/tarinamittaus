import { makeObservable, observable } from "mobx";
import SeliteTypeEnum from "../types/enums/seliteType.enum";
import IAsennuspaikantyyppi from "../types/interfaces/asennuspaikanTyyppi.interface";



export default class AsennuspaikanTyyppiStore implements IAsennuspaikantyyppi {
  selite = SeliteTypeEnum.muu;
  lisatiedot = '';

  constructor() {
    makeObservable(this, {
      selite: observable,
      lisatiedot: observable
    })
  }
}