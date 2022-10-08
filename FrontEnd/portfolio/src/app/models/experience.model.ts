export class experience{
id?: number;
nameEx: string;
titleEx: string;
activityEx: string;
dateStartEx: Date;
dateEndEx: Date;
imageEx: string;
personId: number;

constructor(nameEx: string, titleEx: string, activityEx: string, dateStartEx: Date, dateEndEx: Date,
    imageEx: string, personId: number){
        this.nameEx = nameEx;
        this.titleEx = titleEx;
        this.activityEx = activityEx;
        this.dateStartEx = dateStartEx;
        this.dateEndEx = dateEndEx;
        this.imageEx = imageEx;
        this.personId = personId;
}

}