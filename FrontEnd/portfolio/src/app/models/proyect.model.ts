export class proyect {
    id?: number;
    namePro: string;
    descriptionPro: string;
    linkPro: string;
    imagePro: string;
    dateStartPro: Date;
    dateEndPro: Date;
    personId: number;

    constructor(namePro: string, descriptionPro: string, linkPro:string, imagePro: string,
        dateStartPro: Date, dateEndPro: Date, personId: number){
            this.namePro = namePro;
            this.descriptionPro = descriptionPro;
            this.linkPro = linkPro;
            this.imagePro = imagePro;
            this.dateStartPro = dateStartPro;
            this.dateEndPro = dateEndPro;
            this.personId = personId;
        }
}