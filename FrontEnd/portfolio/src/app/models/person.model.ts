export class person {
    id?: number;
    nameP: string;
    lastNameP: string;
    ageP: Date;
    titleP: string;
    aboutP: string;
    provinceP: string;
    countryP: string;
    imageP: string;

    constructor(nameP: string, lastNameP: string, ageP: Date, titleP: string, aboutP: string, provinceP: string, 
        countryP: string, imageP: string){
        this.nameP = nameP;
        this.lastNameP = lastNameP;
        this.ageP = ageP;
        this.titleP = titleP;
        this.aboutP = aboutP;
        this.provinceP = provinceP;
        this.countryP = countryP;
        this.imageP = imageP;
    }
}