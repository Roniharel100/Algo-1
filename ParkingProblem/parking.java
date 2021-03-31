package ParkingProblem;

/**
 * נתונה חניה מעגלית שאנו לא יודעים את אורכה ואנחנו רוצים לדעת כמה מכוניות יש בחניה
 */

public class parking {

    public static void main(String[] args) {
        linked_list_implement parking = new linked_list_implement();
        final int nLetters = 23, size = 13;
        final char v='v',  w = 'w';
        for (int i = 0; i < size; i++) {// יוצרים חניה רנדומלית בגודל ה-size
            char c = (char) ('a' + (int) (Math.random() * nLetters));
            parking.add(c);
        }
        System.out.println(parking);
        System.out.println("number of cars by one pointer = " + calcCars(parking,v,w));
        System.out.println("number of cars by two pointers = " + calcCarsPointers(parking));
    }

    /**
     *פיתרון של בעית החניה עם מצביע אחד
     * סיבוכיות:O(n^2)
     */
    public static int calcCars(linked_list_implement parking,char v,char w) {
        parking.getHead().setData(v);//מעדכנים את הראש רשימה להיות v
        node_implement t = parking.getHead().getNext();//הפוינטר
        boolean flag = true; //continue
        int count = 1;//מונה צעדים
        while (flag) {
            if (t.getData() == v) {//אם הגענו לסימון v
                t.setData(w);//נשנה אותו לw
                int i = count;//כמות הצעדים שעשינו כדי להגיע לv
                while (i > 0) {
                    t = t.getPrev();//חוזרים חזרה אחורה
                    i--;
                }
                if (t.getData() == w) flag = false;//אם הגענו לראש הרשימה
                else {
                    count = 1;//מתחילים לספור מחדש
                    t = parking.getHead().getNext();
                }
            } else {
                t = t.getNext();//ממשיכים לספור ולעדכן מונה
                count++;
            }
        }
        return count;//כמות הצעדים שעשינו
    }

    /**
     *  פיתרון של בעית החניה עם שני מצביעים
     *  סיבוכיות:O(n)
     */
    public static int calcCarsPointers(linked_list_implement parking) {
        int result = 1;
        node_implement nodeForward = parking.getHead().getNext();//פוניטר שמתקדם קדימה
        node_implement headNode = parking.getHead();//פוינטר שנמצא בהתחלה
        while (nodeForward != headNode) {//כל עוד הוא לא הגיע אליו
            nodeForward = nodeForward.getNext();//מתקדם קדימה
            result++;
        }
        return result;
    }

}


