export class Experience{
id?: number;
name: string;
title: string;
activity: string;
dateStart: Date;
dateEnd: Date;
image: string;
personId: number;

constructor(name: string, title: string, activity: string, dateStart: Date, dateEnd: Date, image: string, personId: number){
    this.name = name;
    this.title = title;
    this.activity = activity;
    this.dateStart = dateStart;
    this.dateEnd = dateEnd;
    this.image = image;
    this.personId = personId;
}
}