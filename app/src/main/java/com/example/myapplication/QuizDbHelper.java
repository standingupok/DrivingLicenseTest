package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myapplication.QuizContract.*;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;

@SuppressLint("Instantiatable")
public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "a1_license";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    public QuizDbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " +
                CategoryTable.TABLE_NAME + " ( " +
                CategoryTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoryTable.COLUMN_NAME + " TEXT" +
                ")";

        final String SQL_CREATE_TYPE_TABLE = "CREATE TABLE " +
                TypeTable.TABLE_NAME + " ( " +
                TypeTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TypeTable.COLUMN_NAME + " TEXT" +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NUMBER + " INTEGER, " +
                QuestionsTable.COLUMN_IMG + " TEXT, " +
                QuestionsTable.COLUMN_EXPLAIN + " TEXT, " +
                QuestionsTable.COLUMN_SPECIAL + " INTERGER, " +
                QuestionsTable.COLUMN_TYPE + " INTEGER, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_TYPE +") REFERENCES " +
                TypeTable.TABLE_NAME + "(" + TypeTable._ID + ")" +"ON DELETE CASCADE, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID +") REFERENCES " +
                CategoryTable.TABLE_NAME + "(" + CategoryTable._ID + ")" +"ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_CATEGORY_TABLE);
        db.execSQL(SQL_CREATE_TYPE_TABLE);
        fillCategory();
        fillType();
        filQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " +  QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +  CategoryTable.COLUMN_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +  TypeTable.COLUMN_NAME);
        onCreate(db);
    }

    private void filQuestionsTable(){
        //Đề 1

        Question q1 = new Question("Phần của đường bộ được sử dụng cho các phương tiện giao thông qua lại là gì?","Phần mặt đường và lề đường.","Phần đường xe chạy.","Phần đường xe cơ giới.",null,2,"","Phần đường xe chạy là phần của đường bộ được sử dụng cho phương tiện giao thông qua lại.",0,1,1);
        addQuestion(q1);
        Question q2 = new Question("Phương tiện tham gia giao thông đường bộ” gồm những loại nào?","Phương tiện giao thông cơ giới đường bộ."," Phương tiện giao thông thô sơ đường bộ và xe máy chuyên dùng.","Cả ý 1 và ý 2.",null,3,"","Phương tiện giao thông gồm tất cả các loại phương tiện.",0,1,1);
        addQuestion(q2);
        Question q3 = new Question("Sử dụng rượu bia khi lái xe, nếu bị phát hiện thì bị xử lý như thế nào?","Chỉ bị nhắc nhở.","Bị xử phạt hành chính hoặc có thể bị xử lý hình sự tùy theo mức độ vi phạm.","Không bị xử lý hình sự.",null,2,"","Sử dụng rượu, bia khi lái xe bị phạt hành chính hoặc xử lý hình sự.",1,1,1);
        addQuestion(q3);
        Question q4 = new Question("Bạn đang lái xe phía trước có một xe cứu thương đang phát tín hiệu ưu tiên bạn có được phép vượt hay không?","Không được vượt.","Được vượt khi đang đi trên cầu.","Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông.","Được vượt khi đảm bảo an toàn.",1,null,"Không được vượt khi đang phát tín hiệu ưu tiên.",0,1,1);
        addQuestion(q4);
        Question q5 = new Question("Hành vi sử dụng xe mô tô để kéo, đẩy xe mô tô khác bị hết xăng đến trạm mua xăng có được phép hay không?","Chỉ được kéo nếu đã nhìn thấy trạm xăng.","Chỉ được thực hiện trên đường vắng phương tiện cùng tham gia giao thông.","Không được phép.",null,3,"","Xe mô tô không được kéo xe khác.",1,1,1);
        addQuestion(q5);
        Question q6 = new Question("Biển báo hiệu hình tròn có nền xanh lam có hình vẽ màu trắng là loại biển gì dưới đây?","Biển báo nguy hiểm.","Biển báo cấm.","Biển báo hiệu lệnh phải thi hành.","Biển báo chỉ dẫn.",3,"q6","Biển hiệu lệnh: Vòng tròn xanh.",0,1,1);
        addQuestion(q6);
        Question q7 = new Question("Bạn đang lái xe trong khu vực đô thị từ 22 giờ đến 5 giờ sáng hôm sau và cần vượt một xe khác, bạn cần báo hiệu như thế nào để đảm bảo an toàn giao thông?","Phải báo hiệu bằng đèn hoặc còi;","Chỉ được báo hiệu bằng còi.","Phải báo hiệu bằng cả còi và đèn.","Chỉ được báo hiệu bằng đèn.",4,"","Chỉ sử dụng còi từ 5 giờ sáng đến 22 giờ tối. Nên phải sử dụng đèn để báo hiệu.",0,1,1);
        addQuestion(q7);
        Question q8 = new Question("Người điều khiển phương tiện tham gia giao thông trong hầm đường bộ ngoài việc phải tuân thủ các quy tắc giao thông còn phải thực hiện những quy định nào dưới đây?", "Xe cơ giới, xe máy chuyên dùng phải bật đèn; xe thô sơ phải bật đèn hoặc có vật phát sáng báo hiệu; chỉ được dừng xe, đỗ xe ở nơi quy định.","Xe cơ giới phải bật đèn ngay cả khi đường hầm sáng; phải cho xe chạy trên một làn đường và chỉ chuyển làn ở nơi được phép; được quay đầu xe, lùi xe khi cần thiết.", "Xe máy chuyên dùng phải bật đèn ngay cả khi đường hầm sáng; phải cho xe chạy trên một làn đường và chỉ chuyển làn ở nơi được phép; được quay đầu xe, lùi xe khi cần thiết.",null,1,"","Trong hầm chỉ đường dừng xe, đỗ xe ở nơi quy định.",0,1,1);
        addQuestion(q8);
        Question q9 = new Question("Trên đoạn đường hai chiều không có giải phân cách giữa, người lái xe không được vượt xe khác trong các trường hợp nào dưới đây?","Xe bị vượt bất ngờ tăng tốc độ và cố tình không nhường đường. ","Xe bị vượt giảm tốc độ và nhường đường.","Phát hiện có xe đi ngược chiều.","Cả ý 1 và ý 3.",4,"","Không được vượt khi xe bị vượt bất ngờ tăng tốc hoặc phát hiện có xe đi ngược chiều.",0,1,1);
        addQuestion(q9);
        Question q10 = new Question("Khi điều khiển xe chạy với tốc độ dưới 60 km/h, để đảm bảo khoảng cách an toàn giữa hai xe, người lái xe phải điều khiển xe như thế nào?","Chủ động giữ khoảng cách an toàn phù hợp với xe chạy liền trước xe của mình.","Đảm bảo khoảng cách an toàn theo mật độ phương tiện, tình hình giao thông thực tế.","Cả ý 1 và ý 2.",null,3,"","Khoảng cách an toàn dưới 60km/h: Chủ động và đảm bảo khoảng cách.",0,1,1);
        addQuestion(q10);
        Question q11 = new Question("Để báo hiệu cho xe phía trước biết xe mô tô của bạn muốn vượt, bạn phải có tín hiệu như thế nào dưới đây?","Ra tín hiệu bằng tay rồi cho xe vượt qua.","Tăng ga mạnh để gây sự chú ý rồi cho xe vượt qua.","Bạn phải có tín hiệu bằng đèn hoặc còi.",null,3,"","Muốn vượt xe thì phải báo hiệu bằng đèn hoặc còi.",0,1,1);
        addQuestion(q11);
        Question q12 = new Question("Khi điều khiển xe mô tô tay ga xuống đường dốc dài, độ dốc cao, người lái xe cần thực hiện các thao tác nào dưới đây để đảm bảo an toàn?","Giữ tay ga ở mức độ phù hợp, sử dụng phanh trước và phanh sau để giảm tốc độ.","Nhả hết tay ga, tắt động cơ, sử dụng phanh trước và phanh sau để giảm tốc độ.","Sử dụng phanh trước để giảm tốc độ kết hợp với tắt chìa khóa điện của xe.",null,1,"","Xe mô tô xuống dốc dài cần sử dụng cả phanh trước và phanh sau để giảm tốc độ.",1,3,1);
        addQuestion(q12);
        Question q13 = new Question("Tay ga trên xe mô tô hai bánh có tác dụng gì trong các trường hợp dưới đây?","Để điều khiển xe chạy về phía trước.","Để điều tiết công suất động cơ qua đó điều khiển tốc độ của xe.","Để điều khiển xe chạy lùi.","Cả ý 1 và ý 2.",4,"",null,0,3,1);
        addQuestion(q13);
        Question q14 = new Question("Biển nào cấm xe rẽ trái?","Biển 1.","Biển 2.","Cả hai biển.",null,1,"q14","P. 123a Cấm rẽ trái không cấm quay đầu xe; Biển 2: P.124a “Cấm quay xe” không cấm rẽ trái. Nên Biển 1 là đáp án đúng.",0,4,1);
        addQuestion(q14);
        Question q15 = new Question("Biển nào dưới đây các phương tiện không được phép đi vào?","Biển 1.","Biển 2.","Biển 1 và 2.",null,3,"q15","Biển 1: P.101 Đường cấm; Biển 2: P.102 Cấm đi ngược chiều cấm đi vào theo chiều đặt biển; Biển 3: P.301a Cấm đỗ xe. Nên biển 1 và biển 2 là cấm các phương tiện không được phép đi vào.",0,4,1);
        addQuestion(q15);
        Question q16 = new Question("Biển nào xe mô tô hai bánh không được đi vào?","Biển 1","Biển 2","Biển 3",null,2,"q16","Biển chính là P.101 “Đường cấm”, có biển phụ thì áp dụng cấm theo biển phụ. Nên Biển 2 áp dụng với xe mô tô là câu trả lời đúng.",0,4,1);
        addQuestion(q16);
        Question q17 = new Question("Biển nào báo hiệu nguy hiểm giao nhau với đường sắt?","Biển 1 và 2","Biển 1 và 3","Biển 2 và 3","Cả 3 biển",2,"q17","Biển 1: W.210 “Giao nhau với đường sắt có rào chắn”; Biển 2: W.234 “Giao nhau với đường 2 chiều”; Biển 3: W.242a “Nơi đường sắt giao vuông gốc với đường bộ”.",0,4,1);
        addQuestion(q17);
        Question q18 = new Question("Biển nào báo hiệu “Đường giao nhau” của các tuyến đường cùng cấp?","Biển 1","Biển 2","Biển 3",null,1,"q18","Biển 1: W.205a “Đường giao nhau cùng cấp; Biển 2: W.207a “Giao nhauvới đường không ưu tiên”; Biển 3: W.208 “Giao nhau với đường ưu tiên”.",0,4,1);
        addQuestion(q18);
        Question q19 = new Question("Biển nào chỉ dẫn nơi bắt đầu đoạn đường dành cho người đi bộ?","Biển 1","Biển 2","Biển 3",null,2,"q19","W.224 “Đường người đi bộ cắt ngang”;Biển 2: I.423c “Điểm bắt đầu đường đi bộ”;Biển3: W.225 “trẻ em”",0,4,1);
        addQuestion(q19);
        Question q20 = new Question("Biển nào dưới đây báo hiệu hết cấm vượt?","Biển 1","Biển 2","Biển 3","Biển 2 và 3",4,"q20","Biển 1: DP134 “Hết hạn chế tốc độ tối đa”; Biển 2: DP135 “Hết mọi lệnh cấm”; Biển 3: DP.133 “Hết cấm vượt”; Nên đáp án đúng phải là Biển 2 và Biển 3.",0,4,1);
        addQuestion(q20);
        Question q21 = new Question("Vạch kẻ đường nào dưới đây là vạch phân chia hai chiều xe chạy (vạch tim đường), xe không được lấn làn, không được đè lên vạch?","Vạch 1","Vạch 2","Vạch 3","Cả 3 vạch",2,"q21","Vạch 1: Vạch phân chia các làn xe cùng chiều, dạng vạch đơn, đứt nứt. Vạch 2: Vạch phân chia hai chiều xe chạy, dạng vạch đơn, nét liền. Vạch 3: Vạch phân chia các làn xe cùng chiều, dạng vạch đơn, nét liền. Vì vậy chọn vạch 2.",0,4,1);
        addQuestion(q21);
        Question q22 = new Question("Xe nào được quyền đi trước trong trường hợp này?","Mô tô","Xe cứu thương",null,null,2,"q22","Xe ưu tiên đi trước.",0,5,1);
        addQuestion(q22);
        Question q23 = new Question("Xe tải kéo mô tô ba bánh như hình này có đúng quy tắc giao thông không?","Đúng","Không đúng",null,null,2,"q23","Xe tải kéo xe mô tô 3 bánh như trên hình là không đúng vì đi vào đường có biển P.108 “Cấm ô tô kéo moóc” kể cả xe máy, xe ô tô khách kéo theo rơ moóc đi qua.",0,5,1);
        addQuestion(q23);
        Question q24 = new Question("Theo hướng mũi tên, những hướng nào xe mô tô được phép đi?","Cả ba hướng","Hướng 1 và 2","Hướng 1 và 3","Hướng 2 và 3",3,"q24","Hướng 2 có biển số P.104 “Cấm mô tô”; Hướng 3 Biển số P.103a “Cấm ô tô” nhưng không cấm mô tô. Nên đáp án đúng là hướng 1 và hướng 3.",0,5,1);
        addQuestion(q24);
        Question q25 = new Question("Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?","Xe con, xe tải, xe khách.","Xe tải, xe khách, xe mô tô.","Xe khách, xe mô tô, xe con.","Cả bốn xe",2,"q25","Xe con trong cả 2 phía đều đang đúng làn đường và đi theo hướng mà đèn xanh đang bật nên đúng quy tắc giao thông. Xe tải trong cả 2 phía đều ở sai làn đường so với hướng rẽ nên vi phạm quy tắc giao thông.",0,5,1);
        addQuestion(q25);

        //Đề 2
        Question q26 = new Question("“Làn đường” là gì?","Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, sử dụng cho xe chạy."," Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, có bề rộng đủ cho xe chạy an toàn.","Là đường cho xe ô tô chạy, dừng, đỗ an toàn.",null,2,"","Làn đường có bề rộng đủ cho xe chạy an toàn.",0,1,2);
        addQuestion(q26);
        Question q27 = new Question("Người tham gia giao thông đường bộ” gồm những đối tượng nào?","Người điều khiển, người sử dụng phương tiện tham gia giao thông đường bộ.","Người điều khiển, dẫn dắt súc vật; người đi bộ trên đường bộ."," Cả ý 1 và ý 2.",null,3,"","Người tham gia giao thông gồm người sử dụng phương tiện và người đi bộ.",0,1,2);
        addQuestion(q27);
        Question q28 = new Question("Theo Luật phòng chống tác hại của rượu, bia, đối tượng nào dưới đây bị cấm sử dụng rượu bia khi tham gia giao thông? "," Người điều khiển: Xe ô tô, xe mô tô, xe đạp, xe gắn máy."," Người ngồi phía sau người điều khiển xe cơ giới."," Người đi bộ."," Cả ý 1 và ý 2.",1,"","Người điều khiển bị cấm sử dụng rượu, bia khi tham gia giao thông.",1,1,2);
        addQuestion(q28);
        Question q29 = new Question("Người điều khiển xe mô tô hai bánh, ba bánh, xe gắn máy có được phép sử dụng xe để kéo hoặc đẩy các phương tiện khác khi tham gia giao thông không?"," Được phép."," Nếu phương tiện được kéo, đẩy có khối lượng nhỏ hơn phương tiện của mình."," Tuỳ trường hợp."," Không được phép.",4,"","Khoản 3, Điều 30 - Luật Giao thông đường bộ năm 2008 quy định người điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy không được thực hiện các hành vi sau: Sử dụng xe để kéo, đẩy xe khác, vật khác, mang, vác và chở vật cồng kềnh;",1,1,2);
        addQuestion(q29);
        Question q30 = new Question("Hành vi vận chuyển đồ vật cồng kềnh bằng xe mô tô, xe gắn máy khi tham gia giao thông có được phép hay không?"," Không được vận chuyển."," Chỉ được vận chuyển khi đã chằng buộc cẩn thận."," Chỉ được vận chuyển vật cồng kềnh trên xe máy nếu khoảng cách về nhà ngắn hơn 2 km.",null,1,"","Xe mô tô không được mang vác vật cồng kềnh.",1,1,2);
        addQuestion(q30);
        Question q31 = new Question("Biển báo hiệu hình chữ nhật hoặc hình vuông hoặc hình mũi tên nền xanh lam là loại biển gì dưới đây?"," Biển báo nguy hiểm."," Biển báo cấm."," Biển báo hiệu lệnh phải thi hành."," Biển báo chỉ dẫn.",4,"q31","Biển chỉ dẫn: Hình vuông hoặc hình chữ nhật xanh.",0,1,2);
        addQuestion(q31);
        Question q32 = new Question("Khi điều khiển xe chạy trên đường biết có xe sau xin vượt nếu đủ điều kiện an toàn người lái xe phải làm gì?"," Tăng tốc độ và ra hiệu cho xe sau vượt, không được gây trở ngại cho xe sau vượt."," Người điều khiển phương tiện phía trước phải giảm tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua, không được gây trở ngại cho xe sau vượt."," Cho xe tránh về bên trái mình và ra hiệu cho xe sau vượt; nếu có chướng ngại vật phía trước hoặc thiếu điều kiện an toàn chưa cho vượt được phải ra hiệu cho xe sau biết; cấm gây trở ngại cho xe xin vượt.",null,2,"",null,0,1,2);
        addQuestion(q32);
        Question q33 = new Question("Người ngồi trên xe mô tô 2 bánh, xe gắn máy phải đội mũ bảo hiểm có cài quai đúng quy cách khi nào?"," Khi tham gia giao thông đường bộ."," Chỉ khi đi trên đường chuyên dùng; đường cao tốc."," Khi tham gia giao thông trên đường tỉnh lộ hoặc quốc lộ.",null,1,"","Khi tham gia giao thông đường bộ bắt buộc phải đội mũ bảo hiểm.",1,1,2);
        addQuestion(q33);
        Question q34 = new Question("Người lái xe mô tô xử lý như thế nào khi cho xe mô tô phía sau vượt?"," Nếu đủ điều kiện an toàn, người lái xe phải giảm tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua, không được gây trở ngại đối với xe xin vượt."," Lái xe vào lề đường bên trái và giảm tốc độ để xe phía sau vượt qua, không được gây trở ngại đối với xe xin vượt."," Nếu đủ điều kiện an toàn, người lái xe phải tăng tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua.",null,1,"",null,0,1,2);
        addQuestion(q34);
        Question q35 = new Question("Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép đến mức cần thiết, chú ý quan sát và chuẩn bị sẵn sàng những tình huống có thế xảy ra để phòng ngừa tai nạn trong các trường hợp nào dưới đây?"," Gặp biển báo nguy hiểm trên đường."," Gặp biển chỉ dẫn trên đường."," Gặp biển báo hết mọi lệnh cấm."," Gặp biển báo hết hạn chế tốc độ tối đa cho phép.",1,"","Giảm tốc độ, chú ý quan sát khi gặp biển báo nguy hiểm.",0,1,2);
        addQuestion(q35);
        Question q36 = new Question("Người điều khiển xe mô tô phải giảm tốc độ và hết sức thận trọng khi qua những đoạn đường nào dưới đây?"," Đường ướt, đường có sỏi cát trên nền đường."," Đường hẹp có nhiều điểm giao cắt từ hai phía."," Đường đèo dốc, vòng liên tục."," Tất cả các ý nêu trên.",4,"","iảm tốc độ trên đường ướt, đường hẹp và đèo dốc.",0,1,2);
        addQuestion(q36);
        Question q37 = new Question("Khi quay đầu xe, người lái xe cần phải quan sát và thực hiện các thao tác nào để đảm bảo an toàn giao thông?"," Quan sát biển báo hiệu để biết nơi được phép quay đầu; quan sát kỹ địa hình nơi chọn để quay đầu; lựa chọn quỹ đạo quay đầu xe cho thích hợp; quay đầu xe với tốc độ thấp; thường xuyên báo tín hiệu để người, các phương tiện xung quanh được biết; nếu quay đầu xe ở nơi nguy hiểm thì đưa đầu xe về phía nguy hiểm đưa đuôi xe về phía an toàn."," Quan sát biển báo hiệu để biết nơi được phép quay đầu; quan sát kỹ địa hình nơi chọn để quay đầu; lựa chọn quỹ đạo quay đầu xe cho thích hợp; quay đầu xe với tốc độ thấp; thường xuyên báo tín hiệu để người, các phương tiện xung quanh được biết; nếu quay đầu xe ở nơi nguy hiểm thì đưa đầu xe về phía nguy hiểm đưa đuôi xe về phía an toàn.",null,null,1,"","Thực hiện quay đầu xe với tốc độ thấp.",0,3,2);
        addQuestion(q37);
        Question q38 = new Question("Gương chiếu hậu của xe mô tô hai bánh có tác dụng gì trong các trường hợp dưới đây?"," Để quan sát an toàn phía bên trái khi chuẩn bị rẽ trái."," Để quan sát an toàn phía bên phải khi chuẩn bị rẽ phải."," Để quan sát an toàn phía sau cả bên trái và bên phải trước khi chuyển hướng."," Để quan sát an toàn phía trước cả bên trái và bên phải trước khi chuyển hướng.",3,"",null,0,3,2);
        addQuestion(q38);
        Question q39 = new Question("Khi gặp biển nào xe được rẽ trái?","Biển 1","Biển 2","Không biển nào",null,2,"q39","P. 123a “Cấm rẽ trái” không cấm quay đầu xe; Biển 2: P.124a “Cấm quay xe” không cấm rẽ trái. Nên Biển 2 là đáp án đúng.",0,4,2);
        addQuestion(q39);
        Question q40 = new Question("Khi gặp biển nào xe ưu tiên theo luật định vẫn phải dừng lại?","Biển 1","Biển 2","Cả ba biển",null,2,"q40","P.101 “Đườn cấm” không cấm xe ưu tiên; Biển 2: R.122 “Dừng lại” áp dụng với cả xe ưu tiên;Biển 3: P.102 “Cấm đi ngược chiều” không áp dụng với xe ưu tiên;Nên Biển 2 là đáp án đúng, áp dụng với cả xe ưu tiên.",0,4,2);
        addQuestion(q40);
        Question q41 = new Question("Biển báo nào báo hiệu bắt đầu đoạn đường vào phạm vi khu dân cư, các phương tiện tham gia giao thông phải tuân theo các quy định đi đường được áp dụng ở khu đông dân cư?","Biển 1","Biển 2",null,null,1,"q41",null,0,4,2);
        addQuestion(q41);
        Question q42 = new Question("Biển nào báo hiệu Đường sắt giao nhau với đường bộ không có rào chắn?","Biển 1 và 2","Biển 1 và 3","Biển 2 và 3","Cả ba biển",3,"q42","Biển 1: W.210 “Giao nhau với đường sắt có rào chắn”; Biển 2: W.211a “Giao nhau với đường sắt không có rào chắn”; Biển 3: W.242a “Nơi đường sắt giao vuông gốc với đường bộ” ổ sung cho biển W.211a. Nên cả 2 biển 2 và 3 đều đúng.",0,4,2);
        addQuestion(q42);
        Question q43 = new Question("Biển nào báo hiệu “Đường đôi”?","Biển 1","Biển 2","Biển 3",null,2,"q43",null,0,4,2);
        addQuestion(q43);
        Question q44 = new Question("Biển báo này có ý nghĩa gì","Báo hiệu đường có ổ gà, lồi lõm.","Báo hiệu đường có gồ giảm tốc phía trước.",null,null,2,"q44","Biển W221b “Đường có sóng mấp mô nhân tạo”.",0,4,2);
        addQuestion(q44);
        Question q45 = new Question("Trong các biển dưới đây biển nào là biển “Hết mọi lệnh cấm”?","Biển 1","Biển 2","Biển 3","Cả ba biển",2,"q45","Biển 1: DP134 “Hết hạn chế tốc độ tối đa”; Biển 2: DP135 “Hết mọi lệnh cấm”; Biển 3: R307 “Hết hạn chế tốc độ tối thiểu”.",0,4,2);
        addQuestion(q45);
        Question q46 = new Question("Vạch kẻ đường nào dưới đây là vạch phân chia hai chiều xe chạy (vạch tim đường)?","Vạch 1","Vạch 2","Vạch 3","Vạch 1 và 3",4,"q46",null,0,4,2);
        addQuestion(q46);
        Question q47 = new Question("Theo tín hiệu đèn, xe nào được phép đi?","Xe con và xe khách.","Mô tô.",null,null,1,"q47","Xe con và xe khách đang ở làn đường có tín hiệu đèn xanh nên được phép đi.",0,5,2);
        addQuestion(q47);
        Question q48 = new Question("Xe nào được quyền đi trước trong trường hợp này?","Xe con","Xe mô tô",null,null,2,"q48","Cả 2 xe đều gặp đèn xanh nên áp dụng quy tắc đường cùng cấp: Bên phải trống – Rẽ phải – Đi thẳng – Rẽ trái. Nên đáp án đúng là xe mô tô rẽ phải được quyền đi trước. Xe con rẽ trái phải nhường đường.",0,5,2);
        addQuestion(q48);
        Question q49 = new Question("Trong trường hợp này, thứ tự xe đi như thế nào là đúng quy tắc giao thông?","Xe công an, xe quân sự, xe con + mô tô.","Xe quân sự, xe công an, xe con + mô tô.","Xe mô tô + xe con, xe quân sự, xe công an.",null,2,"q49","Thứ tự ưu tiên: Xe ưu tiên – Đường ưu tiên – Bên phải trống – Rẽ phải – Đi thẳng – Rẽ trái. 1. Xe quân sự: Xe ưu tiên cùng cấp xe công an nhưng đi thẳng; 2. Xe công an: Xe ưu tiên; rẽ trái; 3. Xe con và xe mô tô: Cùng đi thẳng.",0,5,2);
        addQuestion(q49);
        Question q50 = new Question("Các xe đi theo hướng mũi tên, xe nào chấp hành đúng quy tắc giao thông?","Xe tải, mô tô.","Xe khách, mô tô.","Xe tải, xe con.","Mô tô, xe con.",2,"q50","Xe con và xe tải: Vi phạm tín hiệu đèn; Xe khách và xe mô tô: Đúng làn đường và đúng tín hiệu đèn.",0,5,2);
        addQuestion(q50);

        //Đề 3
        //Question q = new Question("","","","",null,1,"",null,0,3);
        //addQuestion(q);
        Question q51 = new Question("Trong các khái niệm dưới đây, dải phân cách được hiểu như thế nào là đúng?","Là bộ phận của đường để ngăn cách không cho các loại xe vào những nơi không được phép.","Là bộ phận của đường để phân tách phần đường xe chạy và hành lang an toàn giao thông.","Là bộ phận của đường để phân chia mặt đường thành hai chiều xe chạy riêng biệt hoặc để phân chia phần đường của xe cơ giới và xe thô sơ.",null,3,"","Dải phân cách là bộ phận của đường để phân chia mặt đường thành hai chiều xe chạy riêng biệt hoặc để phân chia phần đường của xe cơ giới và xe thô sơ.",0,1,3);
        addQuestion(q51);
        Question q52 = new Question("Người điều khiển phương tiện tham gia giao thông đường bộ gồm những đối tượng nào dưới đây?","Người điều khiển xe cơ giới, người điều khiển xe thô sơ.","Người điều khiển xe máy chuyên dùng tham gia giao thông đường bộ.","Cả ý 1 và ý 2.",null,3,"","Người điều khiển phương tiện giao thông bao gồm xe: cơ giới, thô sơ, xe máy chuyên dùng.",0,1,3);
        addQuestion(q52);
        Question q53 = new Question("Hành vi điều khiển xe cơ giới chạy quá tốc độ quy định, giành đường, vượt ẩu có bị nghiêm cấm hay không?","Bị nghiêm cấm tuỳ từng trường hợp.","Không bị nghiêm cấm.","Bị nghiêm cấm.",null,3,"","Điều khiển xe cơ giới chạy quá tốc độ quy định, giành đường, vượt ẩu là hành vi bị nghiêm cấm dựa theo Khoản 11 Điều 8 Luật Giao thông đường bộ 2008.",1,1,3);
        addQuestion(q53);
        Question q54 = new Question("Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy có được phép hay không?","Được phép.","Tuỳ trường hợp.","Không được phép.",null,3,"","Tất cả hành vi buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy là hành động gây mất an toàn giao thông nghiêm trọng.",1,1,3);
        addQuestion(q54);
        Question q55 = new Question("Người đủ bao nhiêu tuổi trở lên thì được điều khiển xe mô tô hai bánh, xe mô tô ba bánh có dung tích xi lanh từ 50 cm3 trở lên và các loại xe có kết cấu tương tự; xe ô tô tải, máy kéo có trọng tải dưới 3,5 tấn; xe ô tô chở người đến 9 chỗ ngồi","16 tuổi.","18 tuổi.","17 tuổi.",null,2,"","Người đủ trên 18 tuổi được phép điều khiển xe mô tô 2 bánh có dung tích xi lanh từ 50cc đến dưới 175cc và các xe ô tô tải từ 4 - 9 chỗ - trọng tải dưới 3.5 tấn theo quy định.",0,1,3);
        addQuestion(q55);
        Question q56 = new Question("Khi sử dụng giấy phép lái xe đã khai báo mất để điều khiển phương tiện cơ giới đường bộ, ngoài việc bị thu hồi giấy phép lái xe, chịu trách nhiệm trước pháp luật, người lái xe không được cấp giấy phép lái xe trong thời gian bao nhiêu năm?","02 năm","03 năm","05 nam","04 năm.",3,"","Người có hành vi sử dụng giấy phép lái xe đã khai báo mất để điều khiển phương tiện cơ giới đường bộ, có hành vi gian dối khác để được đổi, cấp lại, cấp mới giấy phép lái xe sẽ bị cơ quan quản lý giấy phép lái xe ra quyết định thu hồi giấy phép lái xe, hồ sơ gốc và cập nhật dữ liệu quản lý trên hệ thống. Phải chịu trách nhiệm trước pháp luật và không được cấp giấy phép lái xe trong thời hạn 5 năm kể từ ngày phát hiện vi phạm. Nếu có nhu cầu cấp lại giấy phép lái xe phải học và sát hạch như trường hợp cấp giấy phép lái xe lần đầu.",0,1,3);
        addQuestion(q56);
        Question q57 = new Question("Khi muốn chuyển hướng, người lái xe phải thực hiện như thế nào để đảm bảo an toàn giao thông?","Quan sát gương, ra tín hiệu, quan sát an toàn và chuyển hướng","Quan sát gương, giảm tốc độ, ra tín hiệu chuyển hướng, quan sát an toàn và chuyển hướng.","Quan sát gương, tăng tốc độ, ra tín hiệu và chuyển hướng",null,2,"","Khi muốn chuyển hướng sang làn đường khác người điều khiển phương tiện phải quan sát gương, giảm tốc độ, ra tín hiệu chuyển hướng, quan sát an toàn và chuyển hướng. Như vậy mới đảm bảo an toàn cho mình và các phương tiện xung quanh.",0,1,3);
        addQuestion(q57);
        Question q58 = new Question("Người điểu khiển xe mô tô hai bánh, xe gắn máy được phép chở tối đa 2 người trong những trường hợp nào?","Chở người bệnh đi cấp cứu; trẻ em dưới 14 tuổi.","Áp giải người có hành vi vi phạm pháp luật.","Cả ý 1 và ý 2.",null,3,"","Người điểu khiển xe mô tô hai bánh, xe gắn máy được phép chở tối đa 2 người khi chở người bị bệnh đi cấp cứu; trẻ em dưới 14 tuổi và áp tải người có hành vi vi phạm pháp luật.",0,1,3);
        addQuestion(q58);
        Question q59 = new Question("Trong các trường hợp dưới đây, để đảm bảo an toàn khi tham gia giao thông, người lái xe mô tô cần thực hiện như thế nào?","Phải đội mũ bảo hiểm đạt chuẩn, có cài quai đúng quy cách, mặc quần áo gọn gàng; không sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính).","Phải đội mũ bảo hiểm khi trời mưa gió hoặc trời quá nắng; có thể sử dụng ô, điện thoại di động, thiết bị âm thanh nhưng phải đảm bảo an toàn.","Phải đội mũ bảo hiểm khi cảm thấy mất an toàn giao thông hoặc khi chuẩn bị di chuyển quãng đường xa.",null,1,"","Để đảm bảo an toàn khi tham gia giao thông, người lái xe mô tô KHÔNG ĐƯỢC PHÉP sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính). Luôn luôn đội mũ bảo hiểm khi điều khiển phương tiện tham gia giao thông.",0,1,3);
        addQuestion(q59);
        Question q60 = new Question("Các phương tiện tham gia giao thông đường bộ (kể cả những xe có quyền ưu tiên) đều phải dừng lại bên phải đường của mình và trước vạch “dừng xe” tại các điểm giao cắt giữa đường bộ và đường sắt khi có báo hiệu dừng nào dưới đây?","Hiệu lệnh của nhân viên gác chắn.","Đèn đỏ sáng nháy, cờ đỏ, biển đỏ.","Còi, chuông kêu, chắn đã đóng","Tất cả các ý trên.",4,"","Tất cả hành động trên đều là cảnh báo bắt buộc các phương tiện phải tuân thủ khi đi đến tuyến đường đường bộ giao nhau với đường sắt.",0,1,3);
        addQuestion(q60);
        Question q61 = new Question("Khi gặp xe buýt đang đang dừng đón, trả khách, người điều khiển xe mô tô phải xử lý như thế nào dưới đây để đảm bảo an toàn giao thông?","Tăng tốc độ để nhanh chóng vượt qua bến đỗ.","Giảm tốc độ đến mức an toàn có thể và quan sát người qua đường và từ từ vượt qua xe buýt.","Yêu cầu phải dừng lại phía sau xe buýt chờ xe rời bến mới đi tiếp.",null,2,"","Khi xe buýt đang dừng đón trả khách thì người điều khiển xe mô tô: giảm tốc độ và từ từ vượt qua xe buýt. Không nhất thiết phải dừng lại, nhưng bắt buộc không được tăng tốc vượt ẩu vì rất dễ gây tai nạn.",0,1,3);
        addQuestion(q61);
        Question q62 = new Question("Khi tránh nhau trên đường hẹp, người lái xe cần phải chú ý những điểm nào để đảm bảo an toàn giao thông?","Không nên đi cố vào đường hẹp; xe đi ở sườn núi nên dừng lại trước để nhường đường; khi dừng xe nhường đường phải đỗ ngay ngắn.","Trong khi tránh nhau không nên đổi số; khi tránh nhau ban đêm, phải tắt đèn pha bật đèn cốt.","Khi tránh nhau ban đêm, phải thường xuyên bật đèn pha tắt đèn cốt.","Cả ý 1 và ý 2.",4,"","Cả ý 1 và ý 2 đúng. Ý 3 SAI vì tránh nhau không được bật đèn pha mà phải bật đèn cốt.",0,3,3);
        addQuestion(q62);
        Question q63 = new Question("Để đảm bảo an toàn khi tham gia giao thông, người lái xe mô tô hai bánh cần điều khiển tay ga như thế nào trong các trường hợp dưới đây?","Tăng ga thật nhanh, giảm ga từ từ.","Tăng ga thật nhanh, giảm ga thật nhanh.","Tăng ga từ từ, giảm ga thật nhanh.","Tăng ga từ từ, giảm ga từ từ.",3,"","Để đảm bảo an toàn khi tham gia giao thông, người lái xe mô tô hai bánh phải tăng ga từ từ để điều chỉnh tốc độ an toàn và giảm ga thật nhanh nếu thấy không an toàn. Đây là câu hỏi liên quan thực tế, học viên bắt buộc phải nhớ để đi đường sau này.",0,3,3);
        addQuestion(q63);
        Question q64 = new Question("Biển nào cấm các phương tiện giao thông đường bộ rẽ phải?","Biển 1 và 2.","Biển 1 và 3.","Biển 2 và 3.","Cả ba biển.",1,"q64","Biển 1 là biển cấm phương tiện rẽ phải. Biển 2 là Biển báo cấm phương tiện rẽ phải và quay đầu. Biển 3 là biển báo cấm phương tiện XE Ô TÔ rẽ phải và quay đầu.",0,4,3);
        addQuestion(q64);
        Question q65 = new Question("Biển nào cấm tất cả các loại xe cơ giới và thô sơ đi lại trên đường, trừ xe ưu tiên theo luật định (nếu đường vẫn cho xe chạy được)?","Biển 1","Biển 2","Cả 2 biển",null,1,"q65","Biển 1: Biển đường cấm là biển báo giao thông báo đường cấm tất cả các loại phương tiện (cơ giới và thô sơ) đi lại cả hai hướng, trừ các xe được ưu tiên theo quy định. Biển 2: Biển báo cấm STOP có ý nghĩa bắt buộc đối với các phương tiện xe cơ giới và phương tiện xe thô sơ kể cả các phương tiện xe cơ giới được ưu tiên theo quy định của nhà nước phải dừng lại trước biển báo này hay trước vạch kẻ ngang đường. Người tham gia giao thông chỉ được phép đi tiếp khi có tín hiệu đèn, cờ hay tín hiệu của người điều khiển giao thông cho phép được đi tiếp.",0,4,3);
        addQuestion(q65);
        Question q66 = new Question("Gặp biển nào người lái xe phải nhường đường cho người đi bộ?","Biển 1","Biển 2","Biển ","Biển 1 và 3",1,"q66","Biển 1 là biển cảnh báo dường người đi bộ cắt ngang, bắt buộc các phương tiện phải nhường đường. Biển 2 là biển cấm người đi bộ. Biển 3 là biển báo đường dành cho người đi bộ.",0,4,3);
        addQuestion(q66);
        Question q67 = new Question("Biển nào báo hiệu sắp đến chỗ giao nhau giữa đường bộ và đường sắt?","Biển 1","Biển 2","Biển 3","Biển 1 và 3",1,"q67","Biển 1 là biển báo nguy hiểm giao nhau với đường sắt không có rào chắn. Biển 2 là biển báo giao nhau với đường ưu tiên. Biển 3 là biển báo giao nhau với đường tàu điện.",0,4,3);
        addQuestion(q67);
        Question q68 = new Question("Biển nào báo hiệu “Đường đôi”?","Biển 1","Biển 2","Biển 3",null,3,"q68","Biển 1 là biển cảnh báo đường hai chiều. Biển 2 là biển báo giao nhau với đường hai chiều. Biển 3 là biển báo nguy hiểm báo hiệu đường đôi.",0,4,3);
        addQuestion(q68);
        Question q69 = new Question("Biển nào (đặt trước ngã ba, ngã tư) cho phép xe được rẽ sang hướng khác?","Biển 1","Biển 2","Không biển nào",null,3,"q69","Hai biển báo trên đều thuộc biển báo hiệu lệnh bắt buộc phải thi hành. Biển 1 bắt buộc phải đi thẳng, biển 2 chỉ được phép rẽ trái hoặc phải. Khi gặp biển này, các phương tiện không được phép rẽ sang hướng khác.",0,4,3);
        addQuestion(q69);
        Question q70 = new Question("Biển nào chỉ dẫn cho người đi bộ sử dụng cầu vượt qua đường?","Biển 1","Biển 2","Cả hai biển.","Không biển nào.",1,"q70","Biển 1 là biển chỉ dẫn cầu vượt qua đường cho người đi bộ. Biển 2 là biển chỉ dẫn hầm chui qua đường cho người đi bộ",0,4,3);
        addQuestion(q70);
        Question q71 = new Question("Các vạch dưới đây có tác dụng gì?","Phân chia hai chiều xe chạy ngược chiều nhau.","Phân chia các làn xe chạy cùng chiều nhau.",null,null,1,"q71","Vạch 1: Dạng vạch vàng đơn, đứt nét. Dùng để phân chia các làn đường ngược chiều, không có dải phân cách giữa. Xe được phép cắt qua để sử dụng làn ngược chiều từ cả hai phía. Vạch 2: Ý nghĩa tương tự vạch vàng nét đứt, nhưng với nét liền, xe không được lấn làn hoặc đè lên vạch. Vạch này thường sử dụng ở đoạn đường không đảm bảo tầm nhìn vượt xe, nguy cơ tai nạn giao thông đối đầu lớn. Vạch 3: Dùng để phân chia hai chiều xe chạy cho đường có từ 4 làn xe trở lên, không có dải phân cách giữa, xe không được lấn làn, không được đè lên vạch. Vạch này thường sử dụng ở đoạn đường không đảm bảo tầm nhìn vượt xe, nguy cơ tai nạn giao thông đối đầu lớn hoặc ở các vị trí cần thiết khác. Cả 3 vạch trên đều có chức năng phân chia hai chiều xe chạy ngược chiều nhau.",0,4,3);
        addQuestion(q71);
        Question q72 = new Question("Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?","Xe khách, xe tải, mô tô.","Xe tải, xe con, mô tô.","Xe khách, xe con, mô tô.",null,1,"q72",null,0,5,3);
        addQuestion(q72);
        Question q73 = new Question("Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?","Xe con (A), mô tô, xe con (B), xe đạp.","Xe con (B), xe đạp, mô tô, xe con (A).","Xe con (A), xe con (B), mô tô + xe đạp.","Mô tô + xe đạp, xe con (A), xe con (B).",4,"q73","Ở hình trên, chúng ta không thấy các tín hiệu về xe ưu tiên, biển báo nên sẽ giải theo quy tắc xe nào bên phải trống xe đó được đi trước.",0,5,3);
        addQuestion(q73);
        Question q74 = new Question("Trong hình dưới, những xe nào vi phạm quy tắc giao thông?","Xe con (E), mô tô (C).","Xe tải (A), mô tô (D).","Xe khách (B), mô tô (C).","Xe khách (B), mô tô (D).",1,"q74","Chú ý kỹ đến biển báo chỉ dẫn, chúng ta sẽ thấy xe mô tô (C) đang đi vào làn của xe mô tô và xe ô tô (E) đang đi vào làn của xe mô tô. Các xe khác đi đúng làn đường.",0,5,3);
        addQuestion(q74);
        Question q75 = new Question("Các xe đi theo thứ tự nào là đúng quy tắc giao thông đường bộ?","Xe của bạn, mô tô, xe con","Xe con, xe của bạn, mô tô.","Mô tô, xe con, xe của bạn",null,3,"q75","Trong sa hình chúng ta sẽ chú ý kỹ đến phần biển báo tam giác ngược màu vàng, đây là biển báo giao nhau với đường ưu tiên nên bắt buộc chúng ta sẽ nhường đường (đi sau cùng). Và kèm theo bên dưới có 1 biển báo phụ, chỉ dẫn ưu tiên nhường đường cho xe rẽ trái. Vì thế xe mô tô sẽ được đi trước xe ô tô, và xe của chúng ta sẽ phải đi sau cùng.",0,5,3);
        addQuestion(q75);

        //Đề 4
        Question q76 = new Question("Dải phân cách trên đường bộ gồm những loại nào?","Dải phân cách gồm loại cố định và loại di động.","Dải phân cách gồm tường chống ồn, hộ lan cứng và hộ lan mềm.","Dải phân cách gồm giá long môn và biển báo hiệu đường bộ.",null,1,"","Dải phân cách gồm: loại cố định và loại di động.",0,1,4);
        addQuestion(q76);
        Question q77 = new Question("Khái niệm người điều khiển giao thông được hiểu như thế nào là đúng?","Là người điều khiển phương tiện tham gia giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.","Là cảnh sát giao thông, người được giao nhiệm vụ hướng dẫn giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.","Là người tham gia giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.",null,2,"","Người điều khiển giao thông là: cảnh sát giao thông, người được giao nhiệm vụ hướng dẫn giao thông.",0,1,4);
        addQuestion(q77);
        Question q78 = new Question("Khi lái xe trong khu đô thị và đông dân cư trừ các khu vực có biển cấm sử dụng còi, người lái xe được sử dụng còi như thế nào trong các trường hợp dưới đây?","Từ 22 giờ đêm đến 5 giờ sáng.","Từ 5 giờ sáng đến 22 giờ tối.","Từ 23 giờ đêm đến 5 giờ sáng hôm sau.",null,2,"","Chỉ được phép sử dụng còi từ: 5 giờ sáng đến 22 giờ tối trong khu đô thị và đông dân cư.",0,1,4);
        addQuestion(q78);
        Question q79 = new Question("Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi nào không được phép?","Buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy.","Buông một tay; sử dụng xe để chở người hoặc hàng hoá; để chân chạm xuống đất khi khởi hành.","Đội mũ bảo hiểm; chạy xe đúng tốc độ quy định và chấp hành đúng quy tắc giao thông đường bộ.","Chở người ngồi sau dưới 16 tuổi.",1,"","Những hành vi 'Buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy'. Đều là hành vi bị nghiêm cấm, rất dễ gây mất ATGT nghiêm trọng.",1,1,4);
        addQuestion(q79);
        Question q80 = new Question("Người đủ 16 tuổi được điều khiển các loại xe nào dưới đây?","Xe mô tô 2 bánh có dung tích xi-lanh từ 50 cm3 trở lên.","Xe gắn máy có dung tích xi-lanh dưới 50 cm3.","Xe ô tô tải dưới 3,5 tấn; xe chở người đến 9 chỗ ngồi","Tất cả các ý nêu trên.",2,"","Theo quy định 16 tuổi được điều khiển xe gắn máy dung tích dưới 50 cm3.",0,1,4);
        addQuestion(q80);
        Question q81 = new Question("Khi gặp hiệu lệnh như dưới đây của cảnh sát giao thông thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?","Người tham gia giao thông ở các hướng phải dừng lại.","Người tham gia giao thông ở các hướng được đi theo chiều gậy chỉ của cảnh sát giao thông.","Người tham gia giao thông ở phía trước và phía sau người điều khiển được đi tất cả các hướng; người tham gia giao thông ở phía bên phải và phía bên trái người điều khiển phải dừng lại.","Người tham gia giao thông ở phía trước và phía sau người điều khiển phải dừng lại; người tham giao thông ở phía bên phải và bên trái người điều khiển được đi tất cả các hướng.",4,"q81","Người điều khiển giao thông đưa tay giang ngang thì phía trước mặt và sau lưng dừng lại, 2 bên trái phải được đi.",0,1,4);
        addQuestion(q81);
        Question q82 = new Question("Khi tránh xe đi ngược chiều, các xe phải nhường đường như thế nào là đúng quy tắc giao thông?","Nơi đường hẹp chỉ đủ cho một xe chạy và có chỗ tránh xe thì xe nào ở gần chỗ tránh hơn phải vào vị trí tránh, nhường đường cho xe kia đi.","Xe xuống dốc phải nhường đường cho xe đang lên dốc; xe nào có chướng ngại vật phía trước phải nhường đường cho xe không có chướng ngại vật đi trước.","Xe lên dốc phải nhường đường cho xe xuống dốc; xe nào không có chướng ngại vật phía trước phải nhường đường cho xe có chướng ngại vật đi trước.","Cả ý 1 và ý 2.",4,"","Khi tránh xe ngược chiều: nhường đường qua đường hẹp, xe xuống dốc nhường đường xe lên dốc.",0,1,4);
        addQuestion(q82);
        Question q83 = new Question("Người điều khiển xe mô tô hai bánh, xe gắn máy không được thực hiện những hành vi nào dưới đây?","Đi vào phần đường dành cho người đi bộ và phương tiện khác; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính), đi xe dàn hàng ngang.","Chở 02 người; trong đó, có người bệnh đi cấp cứu hoặc trẻ em dưới 14 tuổi hoặc áp giải người có hành vi vi phạm pháp luật.","Điều khiển phương tiện tham gia giao thông trên đường tỉnh lộ hoặc quốc lộ.",null,1,"","Quy định người điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy 'Không' được thực hiện các hành vi sau: 1. Đi xe vào phần đường dành cho người đi bộ và phương tiện khác; 2. Sử dụng xe để kéo, đẩy xe khác, vật khác, mang, vác và chở vật cồng kềnh;",0,1,4);
        addQuestion(q83);
        Question q84 = new Question("Đường bộ trong khu vực đông dân cư gồm những đoạn đường nào dưới đây?","Là đoạn đường nằm trong khu công nghiệp có đông người và phương tiện tham gia giao thông và được xác định cụ thể bằng biển chỉ dẫn địa giới.","Là đoạn đường bộ nằm trong khu vực nội thành phố, nội thị xã, nội thị trấn và những đoạn đường có đông dân cư sinh sống sát dọc theo đường, có các hoạt động ảnh hưởng đến an toàn giao thông; được xác định bằng biển báo hiệu là đường khu đông dân cư.","Là đoạn đường nằm ngoài khu vực nội thành phố, nội thị xã có đông người và phương tiện tham gia giao thông và được xác định cụ thể bằng biển chỉ dẫn địa giới.",null,2,"","Đường bộ trong khu vực đông dân cư 'là đoạn đường bộ nằm trong khu vực nội thành phố, nội thị xã, nội thị trấn và những đoạn đường có đông dân cư sinh sống sát dọc theo đường, có các hoạt động ảnh hưởng đến an toàn giao thông; được xác định bằng biển báo hiệu là đường khu đông dân cư.'",0,1,4);
        addQuestion(q84);
        Question q85 = new Question("Tác dụng của mũ bảo hiểm đối với người ngồi trên xe mô tô hai bánh trong trường hợp xảy ra tai nạn giao thông là gì?","Để làm đẹp.","Để tránh mưa nắng.","Để giảm thiểu chấn thương vùng đầu.","Để các loại phương tiện khác dễ quan sát.",3,"","Đội mũ bảo hiểm để bảo vệ phần đầu và giảm thiểu chấn thương chẳng may xảy ra tai nạn. Không phải để làm đẹp, tránh mưa nắng hay dễ quan sát phương tiện khác.",0,1,4);
        addQuestion(q85);
        Question q86 = new Question("Khái niệm về văn hóa giao thông được hiểu như thế nào là đúng?","Là sự hiểu biết và chấp hành nghiêm chỉnh pháp luật về giao thông; là ý thức trách nhiệm với cộng đồng khi tham gia giao thông.","Là ứng xử có văn hóa, có tình yêu thương con người trong các tình huống không may xảy ra khi tham gia giao thông.","Cả ý 1 và ý 2.",null,3,"","Văn hóa giao thông Là sự hiểu biết và chấp hành nghiêm chỉnh pháp luật về giao thông; là ý thức trách nhiệm với cộng đồng khi tham gia giao thông và Là ứng xử có văn hóa, có tình yêu thương con người trong các tình huống không may xảy ra khi tham gia giao thông.",0,2,4);
        addQuestion(q86);
        Question q87 = new Question("Khi điều khiển xe trên đường vòng người lái xe cần phải làm gì để đảm bảo an toàn?","Quan sát cẩn thận các chướng ngại vật và báo hiệu bằng còi, đèn; giảm tốc độ tới mức cần thiết, về số thấp và thực hiện quay vòng với tốc độ phù hợp với bán kính cong của đường vòng.","Quan sát cẩn thận các chướng ngại vật và báo hiệu bằng còi, đèn; tăng tốc để nhanh chóng qua đường vòng, đạp ly hợp (côn) và giảm tốc độ sau khi qua đường vòng.",null,null,1,"","Để đảm bảo an toàn giao thông khi qua đường vòng, chúng ta cần 'quan sát cẩn thận các chướng ngại vật và báo hiệu bằng còi, đèn; giảm tốc độ tới mức cần thiết, về số thấp và thực hiện quay vòng với tốc độ phù hợp với bán kính cong của đường vòng.' Vừa có thời gian quan sát các phương tiện, vừa có thể xử lý khi có tình huống bất ngờ xảy ra.",0,3,4);
        addQuestion(q87);
        Question q88 = new Question("Kỹ thuật cơ bản để giữ thăng bằng khi điều khiển xe mô tô đi trên đường gồ ghề như thế nào trong các trường hợp dưới đây?","Đứng thẳng trên giá gác chân lái sau đó hơi gập đầu gối và khuỷu tay, đi chậm để không nẩy quá mạnh.","Ngồi lùi lại phía sau, tăng ga vượt nhanh qua đoạn đường xóc.","Ngồi lệch sang bên trái hoặc bên phải để lấy thăng bằng qua đoạn đường gồ ghề.",null,1,"","Khi qua đoạn đường gồ ghề 'BẮT BUỘC' phải giảm tốc, yêu cầu người điều khiển phương tiện mô tô phải thực hiện động tác hơi gập đầu gối và giữ tay lái an toàn.",0,3,4);
        addQuestion(q88);
        Question q89 = new Question("Biển nào cấm các phương tiện giao thông đường bộ rẽ trái?","Biển 1 và 2.","Biển 1 và 3.","Biển 2 và 3.","Cả ba biển.",1,"q89","Biển 1 là biển cấm phương tiện rẽ trái. Biển 2 là Biển báo cấm phương tiện rẽ trái và quay đầu. Biển 3 là biển báo cấm phương tiện XE Ô TÔ rẽ trái và quay đầu.",0,4,4);
        addQuestion(q89);
        Question q90 = new Question("Gặp biển nào xe xích lô được phép đi vào?","Biển 1","Biển 2","Biển 3","Biển 1 và 2",4,"q90","Biển 1 là biển báo cấm phương tiện xe lam. Biển 2 là biển báo cấm phương tiện xích lô máy (xe ba bánh có động cơ). Biển 3 là biển báo cấm xe xích lô (thô sơ - xe bánh không có động cơ). Đáp án đúng câu này là 4 (cả biển 1 và 2). Trường hợp này học viên cần phân biệt rõ xích lô thô sơ và xích lô máy.",0,4,4);
        addQuestion(q90);
        Question q91 = new Question("Biển nào chỉ đường dành cho người đi bộ, các loại xe không được đi vào khi gặp biển này?","Biển 1","Biển 1 và 3","Biển 3","Cả ba biển",3,"q91","Biển 1 là biển cảnh báo Nhường đường cho người đi bộ. Biển 2 là biển cấm người đi bộ. Biển 3 là biển hiệu lệnh, đoạn đường chỉ dành riêng cho người đi bộ.",0,4,4);
        addQuestion(q91);
        Question q92 = new Question("Biển nào báo hiệu, chỉ dẫn xe đi trên đường này được quyền ưu tiên qua nơi giao nhau?","Biển 1 và 2.","Biển 1 và 3.","Biển 2 và 3.","Cả ba biển.",2,"q92","Biển 1 là biển giao nhau với đường không ưu tiên. Biển 2 là biển báo giao nhau với đường ưu tiên. Biển 3 là biển báo bắt đầu đoạn đường ưu tiên. Ở đây biển 1 và 3 là đúng. Vì khi gặp biển 2 (biển giao nhau với đường ưu tiên, bắt buộc chúng ta phải nhường đường cho các xe đi trên đoạn đường này).",0,4,4);
        addQuestion(q92);
        Question q93 = new Question("Biển nào báo hiệu “Giao nhau với đường hai chiều”?","Biển 1","Biển 2","Biển 3",null,1,"q93","Biển 1 là biển báo giao nhau với đường hai chiều. Biển 2 là biển báo bắt đầu đoạn đường đôi. Biển 3 là biển báo đường giao nhau cùng cấp.",0,4,4);
        addQuestion(q93);
        Question q94 = new Question("Biển nào báo hiệu Hướng đi thẳng phải theo","Biển 1","Biển 2",null,null,1,"q94","Biển 1 là biển báo hiệu lệnh BẮT BUỘC phải thi hành, trường hợp này chỉ được đi thẳng. Biển 2 là biển báo Đường một chiều.",0,4,4);
        addQuestion(q94);
        Question q95 = new Question("Biển nào chỉ dẫn cho người đi bộ sử dụng hầm chui qua đường?","Biển 1","Biển 2","Cả hai biển","Không biển nào",2,"q95","Biển 1 là biển chỉ dẫn cầu vượt qua đường cho người đi bộ. Biển 2 là biển chỉ dẫn hầm chui qua đường cho người đi bộ",0,4,4);
        addQuestion(q95);
        Question q96 = new Question("Khi gặp vạch kẻ đường nào các xe được phép đè vạch?","Vạch 1","Vạch 2","Vạch 3","Vạch 1 và vạch 3.",4,"q96","Vạch liền không được phép đè lên và vượt xe, vạch đứt thì được quyền.",0,4,4);
        addQuestion(q96);
        Question q97 = new Question("Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?","Xe khách, xe tải, mô tô, xe con.","Xe con, xe khách, xe tải, mô tô.","Mô tô, xe tải, xe khách, xe con.","Mô tô, xe tải, xe con, xe khách.",3,"q97","Trường hợp này, không có biển báo thì đường tất cả các phương tiện trong sa hình đều bình đẳng. Những xe nào bên phải không vướng thì được phép đi trước, tiếp nối những xe sau.",0,5,4);
        addQuestion(q97);
        Question q98 = new Question("Xe nào được quyền đi trước trong trường hợp này?","Xe mô tô","Xe con",null,null,1,"q98","Ở hình trên chúng ta sẽ chú ý đến biển báo hình con thoi (đây là là biển báo bắt đầu đoạn đường ưu tiên) đi kèm ở dưới có biển phụ ưu tiên nhường đường cho xe rẽ trái. Vì thế xe mô tô sẽ được phép đi trước xe con.",0,5,4);
        addQuestion(q98);
        Question q99 = new Question("Trong hình dưới, những xe nào vi phạm quy tắc giao thông?","Xe con (B), mô tô (C).","Xe con (A), mô tô (C).","Xe con (E), mô tô (D).","Tất cả các loại xe trên.",3,"q99","Chú ý kỹ các biển chỉ dẫn ở trên sa hình, chúng ta sẽ thấy xe con (E) đang đi trên là đường cho xe mô tô và xe mô tô (D) đang đi trên đường dành cho xe khách. Hai phương tiện này đang đi sai làn nên vi phạm quy tắc giao thông.",0,5,4);
        addQuestion(q99);
        Question q100 = new Question("Các xe đi theo thứ tự nào là đúng quy tắc giao thông đường bộ?","Xe của bạn, mô tô, xe con.","Xe con, xe của bạn, mô tô.","Mô tô, xe con, xe của bạn.",null,2,"q100","Cũng như giống như câu hỏi số 22, sa hình không có biển báo thì các phương tiện sẽ là bình đăng. Bên phải xe con không vướng nên sẽ được đi trước và sẽ tới xe của bạn. Cuối cùng sẽ là xe mô tô.",0,5,4);
        addQuestion(q100);

        //Đề 5
        Question q101 = new Question("Người lái xe được hiểu như thế nào trong các khái niệm dưới đây?","Là người điều khiển xe cơ giới.","Là người điều khiển xe thô sơ.","Là người điều khiển xe có súc vật kéo.",null,1,"","Người lái xe là người điều khiển xe cơ giới.",0,1,5);
        addQuestion(q101);
        Question q102 = new Question("Trong các khái niệm dưới đây khái niệm “dừng xe” được hiểu như thế nào là đúng?","Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác.","Là trạng thái đứng yên tạm thời của phương tiện giao thông trong một khoảng thời gian cần thiết đủ để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác.","Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian giữa 02 lần vận chuyển hàng hoá hoặc hành khách.",null,2,"","Dừng xe được hiểu gắn gọn là trạng thái đứng yên tạm thời của phương tiện giao thông.",0,1,5);
        addQuestion(q102);
        Question q103 = new Question("Người lái xe sử dụng đèn như thế nào khi lái xe trong khu đô thị và đông dân cư vào ban đêm?","Bất cứ đèn nào miễn là mắt nhìn rõ phía trước.","Chỉ bật đèn chiếu xa (đèn pha) khi không nhìn rõ đường.","Đèn chiếu xa (đèn pha) khi đường vắng, đèn pha chiếu gần (đèn cốt) khi có xe đi ngược chiều.","Đèn chiếu gần (đèn cốt).",4,"","Theo quy đinh trong khu đô thị và đông dân cư bắt buộc sử dụng đèn chiếu gần.",0,1,5);
        addQuestion(q103);
        Question q104 = new Question("Người ngồi trên xe mô tô hai bánh, ba bánh, xe gắn máy khi tham gia giao thông có được mang, vác vật cồng kềnh hay không?","Được mang, vác tuỳ trường hợp cụ thể.","Không được mang, vác.","Được mang, vác nhưng phải đảm bảo an toàn.","Được mang, vác tùy theo sức khỏe của bản thân.",2,"","Người ngồi trên xe mô tô hai bánh, ba bánh, xe gắn máy khi tham gia giao thông KHÔNG ĐƯỢC mang, vác vật cồng kềnh.",1,1,5);
        addQuestion(q104);
        Question q105 = new Question("Người có GPLX mô tô hạng A1 không được phép điều khiển loại xe nào dưới đây?","Xe mô tô có dung tích xi-lanh 125 cm3.","Xe mô tô có dung tích xi-lanh từ 175 cm3 trở lên.","Xe mô tô có dung tích xi-lanh 100 cm3.",null,2,"","Người có GPLX mô tô hạng A1 được điều khiển phương tiện mô tô 2 bánh có dung tích xi lanh từ 50cc cho đến DƯỚI 175cc",0,1,5);
        addQuestion(q105);
        Question q106 = new Question("Khi gặp hiệu lệnh như dưới đây của cảnh sát giao thông thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?","Người tham gia giao thông ở hướng đối diện cảnh sát giao thông được đi, các hướng khác cần phải dừng lại.","Người tham gia giao thông được rẽ phải theo chiều mũi tên màu xanh ở bục cảnh sát giao thông.","Người tham gia giao thông ở các hướng đều phải dừng lại trừ các xe đã ở trong khu vực giao nhau.","Người ở hướng đối diện cảnh sát giao thông phải dừng lại, các hướng khác được đi trong đó có bạn.",3,"q106","Cảnh sát giao thông giơ tay thẳng đứng thì tất cả các hướng phải dừng lại, trừ xe trong giao lộ được đi.",0,1,5);
        addQuestion(q106);
        Question q107 = new Question("Bạn đang lái xe trên đường hẹp, xuống dốc và gặp một xe đang đi lên dốc, bạn cần làm gì?","Tiếp tục đi vì xe lên dốc phải nhường đường cho mình.","Nhường đường cho xe lên dốc.","Chỉ nhường đường khi xe lên dốc nháy đèn.",null,2,"","Khoản 2, điều 17 Luật giao thông đường bộ quy định: 'Xe xuống dốc phải nhường đường cho xe đang lên dốc'.",0,1,5);
        addQuestion(q107);
        Question q108 = new Question("Người điều khiển xe mô tô hai bánh, xe gắn máy có được đi xe dàn hàng ngang; đi xe vào phần đường dành cho người đi bộ và phương tiện khác; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính) hay không?","Được phép nhưng phải đảm bảo an toàn.","Không được phép.","Được phép tùy từng hoàn cảnh, điều kiện cụ thể.",null,2,"","Người điều khiển xe mô tô hai bánh, xe gắn máy KHÔNG ĐƯỢC đi xe dàn hàng ngang; đi xe vào phần đường dành cho người đi bộ và phương tiện khác; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính).",1,1,5);
        addQuestion(q108);
        Question q109 = new Question("Tốc độ tối đa cho phép đối với xe máy chuyên dùng, xe gắn máy (kể cả xe máy điện) và các loại xe tương tự trên đường bộ (trừ đường cao tốc) không được vượt quá bao nhiêu km/h?","50 km/h","40 km/h","60 km/h",null,2,"Xe máy chuyên dùng, xe gắn máy (kể cả xe máy điện) và các loại xe tương tự tốc độ tối đa 40 km/h.",null,0,1,5);
        addQuestion(q109);
        Question q110 = new Question("Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải xử lý như thế nào là đúng quy tắc giao thông?","Tăng tốc độ qua đường giao nhau để đi trước xe đi trên đường ưu tiên.","Giảm tốc độ qua đường giao nhau để đi trước xe đi trên đường ưu tiên.","Nhường đường cho xe đi trên đường ưu tiên từ bất kỳ hướng nào tới.",null,3,"","Xe trên đường không ưu tiên BẮT BUỘC phải nhường đường cho xe trên đường ưu tiên.",0,1,5);
        addQuestion(q110);
        Question q111 = new Question("Trong các hành vi dưới đây, người lái xe mô tô có văn hóa giao thông phải ứng xử như thế nào?","Điều khiển xe đi bên phải theo chiều đi của mình; đi đúng phần đường, làn đường quy định; đội mũ bảo hiểm đạt chuẩn, cài quai đúng quy cách.","Điều khiển xe đi trên phần đường, làn đường có ít phương tiện tham gia giao thông.","Điều khiển xe và đội mũ bảo hiểm ở nơi có biển báo bắt buộc đội mũ bảo hiểm.",null,1,"","Người lái xe mô tô phải Điều khiển xe đi bên phải theo chiều đi của mình; đi đúng phần đường, làn đường quy định; đội mũ bảo hiểm đạt chuẩn, cài quai đúng quy cách.",0,2,5);
        addQuestion(q111);
        Question q112 = new Question("Để đạt được hiệu quả phanh cao nhất, người lái xe mô tô phải sử dụng các kỹ năng như thế nào dưới đây?","Sử dụng phanh trước.","Sử dụng phanh sau.","Giảm hết ga; sử dụng đồng thời cả phanh sau và phanh trước.",null,1,"","Để đạt được hiệu quả phanh cao nhất, người lái xe mô tô phải giảm ga ở mức tối thiểu và kết hợp giữa thắng trước sau để giảm tốc độ xe an toàn.",0,3,5);
        addQuestion(q112);
        Question q113 = new Question("Biển nào dưới đây xe gắn máy được phép đi vào?","Biển 1","Biển 2","Cả 2 biển",null,3,"q113","Biển 1: Cấm xe môtô không cấm xe gắn máy. Biển 2: Cấm xe ôtô không cấm xe gắn máy. Vậy chọn cả 2 biển.",0,4,5);
        addQuestion(q113);
        Question q114 = new Question("Biển nào cho phép xe rẽ trái?","Biển 1","Biển 2","Không biển nào",null,2,"q114","Biển 1: Cấm rẽ trái. Biển 2: Chỉ dẫn 'khu vực quay đầu xe' nên được phép rẽ trái. Chọn biển 2.",0,4,5);
        addQuestion(q114);
        Question q115 = new Question("Gặp biển nào xe lam, xe xích lô máy được phép đi vào?","Biển 1","Biển 2","Biển 3",null,3,"q115","Biển 1: Cấm xe lam. Biển 2: Cấm xích lô máy. Biển 3: cấm xích lô. Vì biển 3 KHÔNG cấm xe lam và xích lô máy nên 2 xe được phép đi vào. Chọn 3.",0,4,5);
        addQuestion(q115);
        Question q116 = new Question("Biển nào báo hiệu “Đường dành cho xe thô sơ”?","Biển 1","Biển 2","Biển 3",null,1,"q116","Biển 1: Đường dành cho xe thô sơ. Biển 2: Cấm xe đạp. Biển 3: Đường người đi xe đạp cắt ngang.",0,4,5);
        addQuestion(q116);
        Question q117 = new Question("Biển nào báo hiệu “Giao nhau với đường không ưu tiên”?","Biển 1","Biển 2","Biển 3","Biển 2 và 3",1,"q117","Biển 1: Giao nhau với đường không ưu tiên . Biển 2: Giao nhau với đường ưu tiên. Biển 3: Bắt đầu đường ưu tiên.",0,4,5);
        addQuestion(q117);
        Question q118 = new Question("Biển nào báo hiệu “Đường hai chiều”?","Biển 1","Biển 2","Biển 3",null,2,"q118","Biển 1: Đường đồng cấp. Biển 2: Đường hai chiều. Biển 3: Hết đường đôi. Chọn biển 2.",0,4,5);
        addQuestion(q118);
        Question q119 = new Question("Biển nào báo hiệu “Đường một chiều”?","Biển 1","Biển 2","Cả 2 biển",null,2,"q119","Biển 1: Biển báo hiệu lệnh các xe chỉ được đi thẳng. Biển 2: Đường một chiều. Nên chọn biển 2.",0,4,5);
        addQuestion(q119);
        Question q120 = new Question("Biển nào báo hiệu “Nơi đỗ xe dành cho người tàn tật”?","Biển 1","Biển 2","Biền 3",null,2,"q120","Biển 1: Đường dành cho xe thô sơ. Biển 2: Nơi đỗ xe dành cho người tàn tật. Biển 3: Đường dành cho người đi bộ. Nên chọn biển 2.",0,4,5);
        addQuestion(q120);
        Question q121 = new Question("Vạch dưới đây có ý nghĩa gì?","Vị trí dừng xe của các phương tiện vận tải hành khách công cộng.","Báo cho người điều khiển được dừng phương tiện trong phạm vi phần mặt đường có bố trí vạch để tránh ùn tắc giao thông.","Dùng để xác định vị trí giữa các phương tiện trên đường.",null,1,"q121","Vạch 9.2: Vạch quy định vị trí dừng đỗ của phương tiện giao thông công cộng trên đường.",0,5,5);
        addQuestion(q121);
        Question q122 = new Question("Trong trường hợp này xe nào đỗ vi phạm quy tắc giao thông?","Xe tải.","Xe con và mô tô.","Cả ba xe.","Xe con và xe tải.",1,"q122","Biển cấm dừng xe và đỗ xe tải (ở biển phụ) nên chỉ có xe tải là vi phạm.",0,5,5);
        addQuestion(q122);
        Question q123 = new Question("Xe nào vi phạm quy tắc giao thông?","Xe khách","Xe mô tô","Xe con","Xe con và mô tô.",3,"q123","Vạch vàng nét liền theo quy định không được đè vạch nên ôtô quay đầu là sai.",0,5,5);
        addQuestion(q123);
        Question q124 = new Question("Bạn có được phép vượt xe mô tô phía trước không?","Cho phép","Không được vượt.",null,null,2,"q124","Tại nơi đường giao nhau, trên đường có làn đường dành cho người đi bộ cắt ngang thì không được phép vượt.",0,5,5);
        addQuestion(q124);
        Question q125 = new Question("Bạn xử lý như thế nào trong trường hợp này?","Tăng tốc độ, rẽ phải trước xe tải và xe đạp.","Giảm tốc độ, rẽ phải sau xe tải và xe đạp.","Tăng tốc độ, rẽ phải trước xe đạp.",null,2,"q125","Trường hợp này xe tải đã vào giao lộ trước nên sẽ được ưu tiên đi trước. Biển báo phía trước là đường ưu tiên dành cho xe thô sơ nên phải nhường cho xe đạp. Vì vậy xe của bạn giảm tốc độ và rẽ phải sau cùng.",0,5,5);
        addQuestion(q125);


        //Đề 6
        Question q126 = new Question("Đường mà trên đó phương tiện tham gia giao thông được các phương tiện giao thông đến từ hướng khác nhường đường khi qua nơi đường giao nhau, được cắm biển báo hiệu đường ưu tiên là loại đường gì?","Đường không ưu tiên.","Đường tỉnh lộ.","Đường quốc lộ.","Đường ưu tiên.",4,"","Đường ưu tiên thì được cắm biển báo đường ưu tiên.",0,1,6);
        addQuestion(q126);
        Question q127 = new Question("Khái niệm “đỗ xe” được hiểu như thế nào là đúng?","Là trạng thái đứng yên của phương tiện giao thông có giới hạn trong một khoảng thời gian cần thiết đủ để cho người lên, xuống phương tiện đó, xếp dỡ hàng hóa hoặc thực hiện công việc khác.","Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian.",null,null,2,"","Đỗ xe thì đứng yên, không giới hạn thời gian.",0,1,6);
        addQuestion(q127);
        Question q128 = new Question("Trong trường hợp đặc biệt, để được lắp đặt, sử dụng còi, đèn không đúng với thiết kế của nhà sản xuất đối với từng loại xe cơ giới bạn phải đảm bảo yêu cầu nào dưới đây?","Phải đảm bảo phụ tùng do đúng nhà sản xuất đó cung cấp.","Phải được chấp thuận của cơ quan có thẩm quyền.","Phải là xe đăng ký và hoạt động tại các khu vực có địa hình phức tạp.",null,2,"","Muốn lắp thêm, chế thêm cái gì vào xe thì phải được Trung Tâm Đăng Kiểm cấp phép.",0,1,6);
        addQuestion(q128);
        Question q129 = new Question("Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được bám, kéo hoặc đẩy các phương tiện khác không?","Được phép.","Được bám trong trường hợp phương tiện của mình bị hỏng.","Được kéo, đẩy trong trường hợp phương tiện khác bị hỏng.","Không được phép.",4,"","Gặp Đáp án có “KHÔNG ĐƯỢC PHÉP” thì chọn là chắc chắn đúng!",1,1,6);
        addQuestion(q129);
        Question q130 = new Question("Người có GPLX mô tô hạng A1 được phép điều khiển loại xe nào dưới đây?","Xe mô tô hai bánh có dung tích xi-lanh từ 50 cm3 đến dưới 175 cm3.","Xe mô tô ba bánh dùng cho người khuyết tật.","Cả ý 1 và ý 2.",null,3,"","GPLX Hạng A1 được chạy xe từ 50 cm3 đến dưới 175 cm3 và người khuyết tật được dùng GPLX này để chạy xe ba bánh.",0,1,6);
        addQuestion(q130);
        Question q131 = new Question("Tại nơi có biển báo hiệu cố định lại có báo hiệu tạm thời thì người tham gia giao thông phải chấp hành hiệu lệnh của báo hiệu nào?","Biển báo hiệu cố định.","Báo hiệu tạm thời.",null,null,2,"","Biển báo tạm thời luôn ưu tiên vì lúc đó biến xảy ra tại khu vực đó!",0,1,6);
        addQuestion(q131);
        Question q132 = new Question("Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải nhường đường như thế nào là đúng quy tắc giao thông?","Nhường đường cho xe đi ở bên phải mình tới.","Nhường đường cho xe đi ở bên trái mình tới.","Nhường đường cho xe đi trên đường ưu tiên hoặc đường chính từ bất kỳ hướng nào tới.",null,3,"","Xe mình đang đi trên đường không ưu tiên thì phải nhường đường cho xe đi trên đường ưu tiên hoặc đường chính từ bất kỳ hướng nào.",0,1,6);
        addQuestion(q132);
        Question q133 = new Question("Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép (có thể dừng lại một cách an toàn) trong trường hợp nào dưới đây?","Khi có báo hiệu cảnh báo nguy hiểm hoặc có chướng ngại vật trên đường; khi chuyển hướng xe chạy hoặc tầm nhìn bị hạn chế; khi qua nơi đường giao nhau, nơi đường bộ giao nhau với đường sắt; đường vòng; đường có địa hình quanh co, đèo dốc.","Khi qua cầu, cống hẹp; khi lên gần đỉnh dốc, khi xuống dốc, khi qua trường học, khu đông dân cư, khu vực đang thi công trên đường bộ; hiện trường xảy ra tai nạn giao thông.","Khi điều khiển xe vượt xe khác trên quốc lộ, đường cao tốc.","Cả ý 1 và ý 2.",4,"","Khi gặp cảnh báo nguy hiểm thì phải giảm tốc độ, đi từ từ thôi. Gặp cầu hẹp, cống hẹp cũng phải giảm ga, đi chậm qua.",0,1,6);
        addQuestion(q133);
        Question q134 = new Question("Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường đôi có dải phân cách giữa, xe mô tô hai bánh, ô tô chở người đến 30 chỗ tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu?","60 km/h","50 km/h","40 km/h",null,1,"","Có dãy phân cách thì 60km/h, không có dãy phân cách thì 50km/h.",0,1,6);
        addQuestion(q134);
        Question q135 = new Question("Người lái xe phải xử lý như thế nào khi quan sát phía trước thấy người đi bộ đang sang đường tại nơi có vạch đường dành cho người đi bộ để đảm bảo an toàn?","Giảm tốc độ, đi từ từ để vượt qua trước người đi bộ.","Giảm tốc độ, có thể dừng lại nếu cần thiết trước vạch dừng xe để nhường đường cho người đi bộ qua đường.","Tăng tốc độ để vượt qua trước người đi bộ.",null,2,"","Gặp người đi bộ đang đi sang đường thì chúng ta phải giảm tốc độ, dừng lại nhường đường cho người đi bộ qua đường xong thì mới được đi tiếp.",1,1,6);
        addQuestion(q135);
        Question q136 = new Question("Trong các hành vi dưới đây, người lái xe ô tô, mô tô có văn hóa giao thông phải ứng xử như thế nào?","Điều khiển xe đi bên phải theo chiều đi của mình; đi đúng phần đường, làn đường quy định; dừng, đỗ xe đúng nơi quy định; đã uống rượu, bia thì không lái xe.","Điều khiển xe đi trên phần đường, làn đường có ít phương tiện giao thông; dừng xe, đỗ xe ở nơi thuận tiện hoặc theo yêu cầu của hành khách, của người thân.","Dừng và đỗ xe ở nơi thuận tiện cho việc chuyên chở hành khách và giao nhận hàng hóa; sử dụng ít rượu, bia thì có thể lái xe.",null,1,"","Đây là hành vi bị nghiêm cấm. Đã uống rượu bia, thì không được lái xe.",0,2,6);
        addQuestion(q136);
        Question q137 = new Question("Khi đang lái xe mô tô và ô tô, nếu có nhu cầu sử dụng điện thoại để nhắn tin hoặc gọi điện, người lái xe phải thực hiện như thế nào trong các tình huống nêu dưới đây?","Giảm tốc độ để đảm bảo an toàn với xe phía trước và sử dụng điện thoại để liên lạc.","Giảm tốc độ để dừng xe ở nơi cho phép dừng xe sau đó sử dụng điện thoại để liên lạc.","Tăng tốc độ để cách xa xe phía sau và sử dụng điện thoại để liên lạc.",null,2,"","Muốn nghe điện thoại khi đang chạy xe thì tấp vào lề, dừng lại rồi nghe thoải mái. Vừa chạy vừa nghe dễ mất tập trung và không an toàn.",1,3,6);
        addQuestion(q137);
        Question q138 = new Question("Biển nào báo hiệu cấm xe mô tô hai bánh đi vào?","Biển 1","Biển 2","Biển 3",null,1,"q138","Biển 1 cấm xe mô tô đi vào. Biển 2 cấm xe ô tô/ xe hơi đi vào. Biển 3 cấm xe tải đi vào.",0,4,6);
        addQuestion(q138);
        Question q139 = new Question("Biển nào xe quay đầu không bị cấm?","Biển 1","Biển 2","Cả hai biển",null,3,"q139","Biển 1 cấm rẽ trái, không cấm quay đầu xe. Biển 2 cho phép quay đầu xe, cho phép rẽ trái.",0,4,6);
        addQuestion(q139);
        Question q140 = new Question("Biển báo này có ý nghĩa như thế nào?","Tốc độ tối đa cho phép về ban đêm cho các phương tiện là 70 km/h.","Tốc độ tối thiểu cho phép về ban đêm cho các phương tiện là 70 km/h.",null,null,1,"q140","Biển báo có số chính giữa, viền đỏ thì nghĩa là hạn chế tốc độ tối đa cho phép.",0,4,6);
        addQuestion(q140);
        Question q141 = new Question("Biển nào báo hiệu sắp đến chỗ giao nhau nguy hiểm?","Biển 1","Biển 1 và 2","Biển 2 v 3","Cả ba biển",4,"q141","Biển 1 giao nhau với đường sắt có rào chắn. Biển 2 giao nhau với đường ưu tiên, phải nhường. Biển 3 giao nhau có đèn tín hiệu giao thông.",0,4,6);
        addQuestion(q141);
        Question q142 = new Question("Biển nào báo hiệu “Giao nhau với đường ưu tiên”?","Biển 1 và 3","Biển 2","Biển 3",null,2,"q142","Biển 1 giao nhau với đường không ưu tiên, được đi trước. Biển 2 giao nhau với đường ưu tiên, phải nhường. Biển 3 ưu tiên qua nơi giao nhau.",0,4,6);
        addQuestion(q142);
        Question q143 = new Question("Biển nào báo hiệu “Giao nhau với đường hai chiều”?","Biển 1","Biển 2","Biển 3",null,2,"q143","Biển 1 báo hiệu đường hai chiều. Biển 2 giao nhau với đường hai chiều. Biển 3 giao nhau đường cùng cùng cấp.",0,4,6);
        addQuestion(q143);
        Question q144 = new Question("Trong các biển dưới đây biển nào là biển “Hết tốc độ tối đa cho phép”?","Biển 1","Biển 2","Biển 3","Cả ba biển",1,"q144","Biển 1 là Hết tốc độ tối đa cho phép. Biển 2 là Hết mọi lệnh cấm. Biển 3 là Hết giới hạn tốc độ tối thiểu.",0,4,6);
        addQuestion(q144);
        Question q145 = new Question("Gặp biển báo này, người tham gia giao thông phải xử lý như thế nào?","Dừng xe tại khu vực có trạm Cảnh sát giao thông.","Tiếp tục lưu thông với tốc độ bình thường.","Phải giảm tốc độ đến mức an toàn và không được vượt khi đi qua khu vực này.",null,3,"q145","Đây là biển chỉ dẫn nơi đặt trạm cảnh sát giao thông. Các phương tiện phải giảm tốc độ đến mức an toàn và không được vượt khi đi qua khu vực này.",0,4,6);
        addQuestion(q145);
        Question q146 = new Question("Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?","Xe tải, xe khách, xe con, mô tô.","Xe tải, mô tô, xe khách, xe con.","Xe khách, xe tải, xe con, mô tô.","Mô tô, xe khách, xe tải, xe con.",2,"q146","Sa hình có Biển Báo, phân tích biển báo. Xe Con và Xe Khách có biển báo giao nhau với đường ưu tiên nên phải nhường đường. Xe Tải và Xe Mô Tô có biển báo giao nhau với đường không ưu tiên nên được đi trước.",0,5,6);
        addQuestion(q146);
        Question q147 = new Question("Theo hướng mũi tên, những hướng nào xe gắn máy đi được?","Cả ba hướng.","Chỉ hướng 1 và 3.","Chỉ hướng 1.",null,1,"q147","Hướng 1 không có biển cấm. Hướng 2 Cấm Mô Tô. Hướng 3 Cấm Ô Tô/ Xe Hơi. Nên, xe gắn máy được quyền đi cả 3 hướng.",0,5,6);
        addQuestion(q147);
        Question q148 = new Question("Các xe đi như thế nào là đúng quy tắc giao thông?","Các xe ở phía tay phải và tay trái của người điều khiển được phép đi thẳng.","Cho phép các xe ở mọi hướng được rẽ phải.","Tất cả các xe phải dừng lại trước ngã tư, trừ những xe đã ở trong ngã tư được phép tiếp tục đi.",null,3,"q148","CSGT giơ tay thẳng đứng lên thì tất cả các xe phải dừng lại trừ các xe đã vào trong ngã tư.",0,5,6);
        addQuestion(q148);
        Question q149 = new Question("Theo tín hiệu đèn của xe cơ giới, xe nào vi phạm quy tắc giao thông?","Xe mô tô.","Xe ô tô con.","Không xe nào vi phạm.","Cả 2 xe.",4,"q149","Câu sa hình có biển báo nên phân tích biển báo: Xe Con/ Xe Hơi xi nhan trái. Xe Mô Tô xi nhan phải. Biển báo bắt buộc đi thẳng nên cả hai xe đều vi phạm.",0,5,6);
        addQuestion(q149);
        Question q150 = new Question("Xe nào dừng đúng theo quy tắc giao thông?","Xe con.","Xe mô tô.","Cả 2 xe đều đúng.",null,1,"q150","Đậu xe cách đường ray gần nhất ít nhất 5 mét. Xe Con/ Xe Hơi đúng còn mô tô sai.",0,5,6);
        addQuestion(q150);

        //Đề 7
        Question q151 = new Question("Khái niệm “phương tiện giao thông cơ giới đường bộ” được hiểu thế nào là đúng?","Gồm xe ô tô; máy kéo; xe mô tô hai bánh; xe mô tô ba bánh; xe gắn máy; xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng.","Gồm xe ô tô; máy kéo; rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô, máy kéo; xe mô tô hai bánh; xe mô tô ba bánh, xe gắn máy (kể cả xe máy điện) và các loại xe tương tự.",null,null,2,"","Mẹo giải câu Khái Niệm PTGT Cơ Giới hay Thô Sơ thì tìm đáp án có đoạn “Các loại xe tương tự” chọn là chắc chắn đúng.",0,1,7);
        addQuestion(q151);
        Question q152 = new Question("Cuộc đua xe chỉ được thực hiện khi nào?","Diễn ra trên đường phố không có người qua lại.","Được người dân ủng hộ.","Được cơ quan có thẩm quyền cấp phép.",null,3,"","Đua Xe phải được Sở Văn Hóa và Thể Thao cấp Tỉnh, Thành Phố cấp phép.",1,1,7);
        addQuestion(q152);
        Question q153 = new Question("Ở phần đường dành cho người đi bộ qua đường, trên cầu, đầu cầu, đường cao tốc, đường hẹp, đường dốc, tại nơi đường bộ giao nhau cùng mức với đường sắt có được quay đầu xe hay không?","Được phép.","Không được phép.","Tùy từng trường hợp.",null,2,"","Gặp đáp án có “KHÔNG ĐƯỢC PHÉP” thì chọn ngay là chắc chắn đúng.",1,1,7);
        addQuestion(q153);
        Question q154 = new Question("Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được sử dụng ô khi trời mưa hay không?","Được sử dụng.","Chỉ người ngồi sau được sử dụng.","Không được sử dụng.","Được sử dụng nếu không có áo mưa.",3,"","Tham gia giao thông không được sử dụng ô/dù. Gây nguy hiểm khi gió giật mạnh.",1,1,7);
        addQuestion(q154);
        Question q155 = new Question("Biển báo hiệu có dạng hình tròn, viền đỏ, nền trắng, trên nền có hình vẽ hoặc chữ số, chữ viết màu đen là loại biển gì dưới đây?","Biển báo nguy hiểm.","Biển báo cấm.","Biển báo hiệu lệnh.","Biển báo chỉ dẫn.",2,"q155","Hình tròn phải làm theo, thấy màu đỏ nhiều là CẤM.",0,1,7);
        addQuestion(q155);
        Question q156 = new Question("Trên đường có nhiều làn đường cho xe đi cùng chiều được phân biệt bằng vạch kẻ phân làn đường, người điều khiển phương tiện phải cho xe đi như thế nào?","Cho xe đi trên bất kỳ làn đường nào hoặc giữa 02 làn đường nếu không có xe phía trước; khi cần thiết phải chuyển làn đường, người lái xe phải quan sát xe phía trước để bảo đảm an toàn.","Phải cho xe đi trong một làn đường và chỉ được chuyển làn đường ở những nơi cho phép; khi chuyển làn phải có tín hiệu báo trước và phải bảo đảm an toàn.","Phải cho xe đi trong một làn đường, khi cần thiết phải chuyển làn đường, người lái xe phải quan sát xe phía trước để bảo đảm an toàn.",null,2,"","Cho xe đi một làn đường và chỉ chuyển làn ở nơi cho phép.",0,1,7);
        addQuestion(q156);
        Question q157 = new Question("Tại nơi đường giao nhau không có báo hiệu đi theo vòng xuyến, người điều khiển phương tiện phải nhường đường như thế nào là đúng quy tắc giao thông?","Phải nhường đường cho xe đi đến từ bên phải.","Xe báo hiệu xin đường trước xe đó được đi trước.","Phải nhường đường cho xe đi đến từ bên trái.",null,1,"","Giao nhau ngã ba, ngã tư không có báo hiệu đi theo vòng xuyến (Bùng Binh - Đảo An Toàn), thì nhường đường cho xe đi bên tay phải của mình.",0,1,7);
        addQuestion(q157);
        Question q158 = new Question("Tại ngã ba hoặc ngã tư không có đảo an toàn, người lái xe phải nhường đường như thế nào là đúng trong các trường hợp dưới đây?","Nhường đường cho người đi bộ đang đi trên phần đường dành cho người đi bộ sang đường; nhường đường cho xe đi trên đường ưu tiên, đường chính từ bất kỳ hướng nào tới; nhường đường cho xe ưu tiên, xe đi từ bên phải đến.","Nhường đường cho người đi bộ đang đứng chờ đi qua phần đường dành cho người đi bộ sang đường; nhường đường cho xe đi trên đường ngược chiều, đường nhánh từ bất kỳ hướng nào tới; nhường đường cho xe đi từ bên trái đến","Không phải nhường đường.",null,1,"","Giao nhau ngã ba, ngã tư không có đảo an toàn (Bùng Binh), thì nhường đường cho người đi bộ đang sang đường, cho xe trên đường ưu tiên, cho xe đi bên tay phải của mình.",0,1,7);
        addQuestion(q158);
        Question q159 = new Question("Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều không có dải phân cách giữa, xe mô tô hai bánh, ô tô chở người đến 30 chỗ tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu?","60 km/h","50 km/h","40 km/h",null,2,"","Đường hai chiều trong khu dân cư, không có dãy phân cách thì tốc độ tối đa chỉ 50km/h.",0,1,7);
        addQuestion(q159);
        Question q160 = new Question("Theo Luật Giao thông đường bộ, tín hiệu đèn giao thông gồm 3 màu nào dưới đây?","Đỏ - Vàng - Xanh.","Cam - Vàng - Xanh.","Vàng - Xanh dương - Xanh lá.","Đỏ - Cam - Xanh.",1,"","ĐỎ - VÀNG - XANH LÁ.",0,1,7);
        addQuestion(q160);
        Question q161 = new Question("Khi xảy ra tai nạn giao thông, có người bị thương nghiêm trọng, người lái xe và người có mặt tại hiện trường vụ tai nạn phải thực hiện các công việc gì dưới đây?","Thực hiện sơ cứu ban đầu trong trường hợp khẩn cấp; thông báo vụ tai nạn đến cơ quan thi hành pháp luật.","Nhanh chóng lái xe gây tai nạn hoặc đi nhờ xe khác ra khỏi hiện trường vụ tai nạn.","Cả ý 1 và ý 2.",null,1,"","Câu Cẩn Thận. Khi gây tai nạn thì mình phải sơ cứu người bi nạn. Không được bỏ trốn khỏi hiện trường.",0,2,7);
        addQuestion(q161);
        Question q162 = new Question("Những thói quen nào dưới đây khi điều khiển xe mô tô tay ga tham gia giao thông dễ gây tai nạn nguy hiểm?","Sử dụng còi.","Phanh đồng thời cả phanh trước và phanh sau.","Chỉ sử dụng phanh trước.",null,3,"","Chỉ sử dụng phanh trước dễ gây nguy hiểm cho người lái xe phía sau và xe mình.",0,3,7);
        addQuestion(q162);
        Question q163 = new Question("Khi gặp biển nào thì xe mô tô hai bánh được đi vào?","Không biển nào.","Biển 1 và 2","Biển 2 và 3","Cả 3 biển.",3,"q163","Biển báo số 1 cấm xe mô tô. Biển báo số 2 cấm xe ô tô/ xe hơi. Biển báo số 3 cấm xe tải.",0,4,7);
        addQuestion(q163);
        Question q164 = new Question("Biển nào xe được phép quay đầu nhưng không được rẽ trái?","Biển 1","Biêển 2","Cả hai biển",null,1,"q64","Biển số 1 cấm rẽ trái nhưng được phép quay đầu. Biển số 2 chỉ được phép rẽ trái không được đi hướng nào khác.",0,4,7);
        addQuestion(q164);
        Question q165 = new Question("Chiều dài đoạn đường 500 m từ nơi đặt biển này, người lái xe có được phép bấm còi không?","Được phép","Không được phép.",null,null,2,"q165","Gặp đáp án có “KHÔNG ĐƯỢC PHÉP” thì chọn ngay là chắc chắn đúng.",0,4,7);
        addQuestion(q165);
        Question q166 = new Question("Biển nào báo hiệu “Giao nhau với đường sắt có rào chắn”?","Biển 1","Biển 2 và 3","Biển 3",null,1,"q166","Biển số 1 giao nhau với đường sắt có rào chắn. Biển số 2 giao nhau với đường ưu tiên. Biển số 3 giao nhau có tín hiệu đèn.",0,4,7);
        addQuestion(q166);
        Question q167 = new Question("Biển nào báo hiệu “Đường bị thu hẹp”?","Biển 1 và 2","Biển 1 và 3","Biển 2 và 3","Cả ba biển",1,"q167","Biển số 1 và 2 báo hiệu đường hẹp. Biển số 3 báo hiệu đường vòng.",0,4,7);
        addQuestion(q167);
        Question q168 = new Question("Biển nào báo hiệu “Chú ý chướng ngại vật”?","Biển 1","Biền 2 và 3","Cả ba biển",null,2,"q168","Biển số 1 báo hiệu ra khỏi đường đôi. Biển số 2 và 3 báo hiệu chú ý chướng ngại vật.",0,4,7);
        addQuestion(q168);
        Question q169 = new Question("Hiệu lực của biển “Tốc độ tối đa cho phép” hết tác dụng khi gặp biển nào dưới đây?","Biển 1","Biển 2","Biển 3","Biển 1 và 2",4,"q169","Biển 1 là Hết Hạn Chế Tốc Độ Tối Đa. Biển 2 là Hết Tất Cả Lệnh Cấm. Biển 3 là Hết Hạn Chế Tốc Độ Tối Thiểu. Biển 2 có chức năng cao hơn Biển 1 vì nó bỏ hết lệnh cấm nên Cả hai Biển 1 và 2 đều cho phép xe chạy với tốc độ tối đa theo luật cho phép.",0,4,7);
        addQuestion(q169);
        Question q170 = new Question("Biển số 1 có ý nghĩa gì?","Đi thẳng hoặc rẽ trái trên cầu vượt.","Đi thẳng hoặc rẽ phải trên cầu vượt.","Báo hiệu cầu vượt liên thông.",null,3,"q170","Biển 1 Báo hiệu cầu vượt liên thông PHẢI CÓ CHỮ. Biển 2 và 3 báo hiệu cầu vượt cắt ngang đường.",0,4,7);
        addQuestion(q170);
        Question q171 = new Question("Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?","Xe tải, xe con, mô tô.","Xe con, xe tải, mô tô.","Mô tô, xe con, xe tải.","Xe con, mô tô, xe tải.",3,"q171","Sa hình này là ngã tư không có biển báo nên xét là Bình Đẳng. Tại Ngã Tư chỉ có 3 Ngã là có Xe còn 1 Ngã là không có xe nào nên áp dụng nguyên tắc: Bên phải xe nào trống (không có xe nào) thì xe đó được đi trước, lần lượt là các xe còn lại. Nên: Xe mô tô tay phải trống xe nên đi trước, rồi đến xe con và xe tải cuối cùng.",0,5,7);
        addQuestion(q171);
        Question q172 = new Question("Xe nào đỗ vi phạm quy tắc giao thông?","Cả hai xe.","Không xe nào vi phạm.","Chỉ xe mô tô vi phạm.","Chỉ xe tải vi phạm.",1,"q172","Biển phụ màu trắng có mũi tên trước sau màu đen nghĩa là Cấm dừng xe và đỗ xe trước và sau biển báo.",0,5,7);
        addQuestion(q172);
        Question q173 = new Question("Theo hướng mũi tên, xe nào được phép đi?","Mô tô, xe con.","Xe con, xe tải.","Mô tô, xe tải.","Cả ba xe.",3,"q173","CSGT giơ tay hướng nào thì hướng đó được chạy, nhìn hướng nào thì hường đó phải dừng lại",0,5,7);
        addQuestion(q173);
        Question q174 = new Question("Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?","Xe con","Xe tải.","Xe con, xe tải.",null,2,"q174","Mẹo giải sa hình loại này, hỏi XE NÀO VI PHẠM thì chọn đáp án KHÔNG CÓ XE CON chọn là chắc chắn đúng.",0,5,7);
        addQuestion(q174);
        Question q175 = new Question("Xe của bạn đang di chuyển gần đến khu vực giao cắt với đường sắt, khi rào chắn đang dịch chuyển, bạn điều khiển xe như thế nào là đứng quy tắc giao thông?","Quan sát nếu thấy không có tầu thì tăng tốc cho xe vượt qua đường sắt.","Dừng lại trước rào chắn một khoảng cách an toàn.","Ra tín hiệu, yêu cầu người gác chắn tàu kéo chậm Barie để xe bạn qua.",null,2,"q175","Khi Tàu Hỏa/ Xe Lửa đi qua thì chúng ta phải dừng lại.",0,5,7);
        addQuestion(q175);


        //Đề 8
        Question q176 = new Question("Khái niệm phương tiện giao thông thô sơ đường bộ được hiểu thế nào là đúng?"," Gồm xe đạp (kể cả xe đạp máy, xe đạp điện), xe xích lô, xe lăn dùng cho người khuyết tật, xe súc vật kéo và các loại xe tương tự."," Gồm xe đạp (kể cả xe đạp máy, xe đạp điện), xe gắn máy, xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng."," Gồm xe ô tô, máy kéo, rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô, máy kéo.",null,1,"", "Mẹo làm câu PT GT Thô Sơ hoặc PT GT Cơ Giới là tìm đáp án có đoạn các loại xe tương tự để chọn là chắc chắn đúng.",0,1,8);
        addQuestion(q176);
        Question q177 = new Question("Người điều khiển phương tiện giao thông đường bộ mà trong cơ thể có chất ma tuý có bị nghiêm cấm hay không?"," Bị nghiêm cấm."," Không bị nghiêm cấm."," Không bị nghiêm cấm, nếu có chất ma tuý ở mức nhẹ, có thể điều khiển phương tiện tham gia giao thông.",null,1,"","Gặp đáp án có Bị Nghiêm Cấm thì chọn là chắc chắn đúng." ,1,1,8);
        addQuestion(q177);
        Question q178 = new Question("Bạn đang lái xe phía trước có một xe cảnh sát giao thông không phát tín hiệu ưu tiên bạn có được phép vượt hay không?"," Không được vượt."," Được vượt khi đang đi trên cầu."," Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông.","Được vượt khi đảm bảo an toàn.",4,"", "Câu này đọc cẩn thận. CSGT không phát tín hiệu ưu tiên thì được vượt thoải mái.",0,1,8);
        addQuestion(q178);
        Question q179 = new Question("Khi đang lên dốc người ngồi trên xe mô tô có được kéo theo người đang điều khiển xe đạp hay không?"," Chỉ được phép nếu cả hai đội mũ bảo hiểm."," Không được phép."," Chỉ được thực hiện trên đường thật vắng.","Chỉ được phép khi người đi xe đạp đã quá mệt. ",2,"", "Gặp đáp án có ‘Không Được Phép’ thì chọn là chắc chắn đúng. Xe đang lên dốc còn kéo xe khác rất dễ tuột dốc, gây họa cho xe phía sau." ,1,1,8);
        addQuestion(q179);
        Question q180 = new Question("Biển báo hiệu có dạng tam giác đều, viền đỏ, viền màu vàng, trên có hình vẽ màu đen là loại biển gì dưới đây?"," Biển báo nguy hiểm."," Biển báo cấm."," Biển báo hiệu lệnh.","Biển báo chỉ dẫn." ,1,"q180" ,"Biển báo tam giác nền vàng, viền đỏ bên trong hiển thị nội dung bằng màu đen là biển cảnh báo nguy hiểm. Yêu cầu nhớ thật kỹ" ,0,1,8);
        addQuestion(q180);
        Question q181 = new Question("Trên đường một chiều có vạch kẻ phân làn đường, xe thô sơ và xe cơ giới phải đi như thế nào là đúng quy tắc giao thông?"," Xe thô sơ phải đi trên làn đường bên trái trong cùng, xe cơ giới, xe máy chuyên dùng đi trên làn đường bên phải."," Xe thô sơ phải đi trên làn đường bên phải trong cùng; xe cơ giới, xe máy chuyên dùng đi trên làn đường bên trái"," Xe thô sơ đi trên làn đường phù hợp không gây cản trở giao thông, xe cơ giới, xe máy chuyên dùng đi trên làn đường bên phải.",null,2,"", "Xe thô sơ thì chắc chắn đi chậm nên đi sát lề đường bên phải." ,0,1,8);
        addQuestion(q181);
        Question q182 = new Question("Tại nơi đường bộ giao nhau cùng mức với đường sắt chỉ có đèn tín hiệu hoặc chuông báo hiệu, khi đèn tín hiệu màu đỏ đã bật sáng hoặc có tiếng chuông báo hiệu, người tham gia giao thông phải dừng lại ngay và giữ khoảng cách tối thiểu bao nhiêu mét tính từ ray gần nhất?" ,"5 mét."," 3 mét."," 4 mét.",null,1,"", "Theo luật, dừng xe cho Xe Lửa/ Tàu Hỏa đi qua phải cách xa đường ray, hàng rào kéo ít nhất 5 mét mới an toàn." ,0,1,8);
        addQuestion(q182);
        Question q183 = new Question("Khi điều khiển xe cơ giới, người lái xe phải bật đèn tín hiệu báo rẽ trong trường hợp nào sau đây?"," Khi cho xe chạy thẳng."," Trước khi thay đổi làn đường."," Sau khi thay đổi làn đường.",null,2,"","Xi nhan trước khi chuyển làn để xe sau biết mình chuẩn bị rẽ hướng nào, bạn rẽ xong mới xi nhan thì xi nhan chi nữa." ,0,1,8);
        addQuestion(q183);
        Question q184 = new Question("Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều hoặc đường một chiều có một làn xe cơ giới, loại xe nào dưới đây được tham gia giao thông với tốc độ tối đa cho phép là 50 km/h?"," Ô tô con, ô tô tải, ô tô chở người trên 30 chỗ."," Xe gắn máy, xe máy chuyên dùng."," Cả ý 1 và ý 2.",null,1,"", "Trong khu dân cư, xe con, xe tải, xe khách trên 30 chỗ mới được chạy 50km/h. Còn xe máy chuyên dùng, xe gắn máy thì chỉ được chạy 40km/h." ,0,1,8);
        addQuestion(q184);
        Question q185 = new Question("Tại nơi giao nhau, khi đèn điều khiển giao thông có tín hiệu màu vàng, người điều khiển giao thông phải chấp hành như thế nào là đúng quy tắc giao thông?"," Phải cho xe dừng lại trước vạch dừng, trường hợp đã đi quá vạch dừng hoặc đã quá gần vạch dừng nếu dừng lại thấy nguy hiểm thì được đi tiếp."," Trong trường hợp tín hiệu vàng nhấp nháy là được đi nhưng phải giảm tốc độ chú ý quan sát nhường đường cho người đi bộ qua đường"," Nhanh chóng tăng tốc độ, vượt qua nút giao và chú ý đảm bảo an toàn.", "Cả ý 1 và ý 2." ,4,"", "Ý 1: Đèn vàng trong Ý này là Đèn Đỏ, Xanh, Vàng. Ý 2: Đèn vàng trong Ý này là chỉ có Đèn Vàng nhấp nháy báo nguy hiểm mà thôi. Nên khi gặp đèn này thì giảm tốc độ." ,0,1,8);
        addQuestion(q185);
        Question q186 = new Question("Trên đường đang xảy ra ùn tắc những hành vi nào sau đây là thiếu văn hóa khi tham gia giao thông?"," Bấm còi liên tục thúc giục các phương tiện phía trước nhường đường."," Đi lên vỉa hè, tận dùng mọi khoảng trống để nhanh chóng thoát khỏi nơi ùn tắc"," Lấn sang trái đường cố gắng vượt lên xe khác."," Tất cả các ý nêu trên.",4,""," Bấm còi liên tục, đi trên vỉa hè, lấn sang trái khi kẹt xe để giành đi trước là thiếu văn hóa.",0,2,8);
        addQuestion(q186);
        Question q187 = new Question("Khi điều khiển xe mô tô quay đầu người lái xe cần thực hiện như thế nào để đảm bảo an toàn?"," Bật tín hiệu báo rẽ trước khi quay đầu, từ từ giảm tốc độ đến mức có thể dừng lại."," Chỉ quay đầu xe tại những nơi được phép quay đầu."," Quan sát an toàn các phương tiện tới từ phía trước, phía sau, hai bên đồng thời nhường đường cho xe từ bên phải và phía trước đi tới."," Tất cả các ý nêu trên.",4,""," Muốn quay xe thì: 1. Báo tín hiệu để xe sau biết 2. Chỗ nào cho phép mới được quay xe 3. Nhìn đường trước sau cẩn thận, thấy an toàn thì quay xe",0,3,8);
        addQuestion(q187);
        Question q188 = new Question("Biển nào cấm quay đầu xe?","Biển 1.","Biển 2.","Không biển nào.", "Cả 2 biển." ,2,"q188", "Luật mới Biển 1 Cấm Rẽ Trái, Không Cấm Quay Đầu Biển 2 Cấm Quay Đầu, Không Cấm Rẽ Trái",0,4,8);
        addQuestion(q188);
        Question q189 = new Question("Biển nào là biển Cấm đi ngược chiều?", "Biển 1.", "Biển 2.", "Cả ba biển.",null,2,"q189","Biển 1 là biển đường cấm tất cả phương tiện tham gia giao thông, trừ xe ưu tiên theo luật định.",0,4,8);
        addQuestion(q189);
        Question q190 = new Question("Biển nào xe mô tô hai bánh được đi vào?","Biển 1 và 2."," Biển 1 và 3."," Biển 2 và 3",null,2,"q190","Biển 1 Đường Cấm Ô Tô/ Xe Hơi đi vào. Biển 2 Đường Cấm Mô Tô đi vào. Biển 3 Đường Cấm Xe Tải đi vào.",0,4,8);
        addQuestion(q190);
        Question q191 = new Question("Biển nào báo hiệu:Giao nhau có tín hiệu đèn"," Biển 1."," Biển 2."," Biển 3."," Cả ba biển.",3,"q191","Biển 1 Báo Hiệu Giao Nhau Với Đường Sắt có Hàng Rào Chắn. Biển 2 Báo Hiệu Giao Nhau Với Đường Ưu Tiên Biển 3 Giao Nhau Có Tín Hiệu Đèn",0,4,8);
        addQuestion(q191);
        Question q192 = new Question("Khi gặp biển nào, người lái xe phải giảm tốc độ, chú ý xe đi ngược chiều, xe đi ở phía đường bị hẹp phải nhường đường cho xe đi ngược chiều?","Biển 1.","Biển 1 và 3.","Biển 2 và 3.","Cả ba biển",3,"q192","Biển 1 Báo Hiệu Ra Khỏi Đường Đôi. Biển 2 Báo Hiệu Đường Hẹp. Biển 3 Báo Hiệu Đường Hẹp.",0,4,8);
        addQuestion(q192);
        Question q193 = new Question("Gặp biển nào người tham gia giao thông phải đi chậm và thận trọng đề phòng khả năng xuất hiện và di chuyển bất ngờ của trẻ em trên mặt đường?","Biển 1.","Biển 2.",null,null,2,"q193","Biển 1 Báo Hiệu Nhường Đường Cho Người Đi Bộ. Biển 2 Báo Hiệu Trẻ Em Hay Chạy Đi Chơi Ngoài Đường.",0,4,8);
        addQuestion(q193);
        Question q194 = new Question("Trong các biển dưới đây biển nào là biển Hết hạn chế tốc độ tối thiểu ?", "Biển 1." ,"Biển 2."," Biển 3."," Cả ba biển.",3,"q194"," Biển 1 Hết Hạn Chế Tốc Độ Tối Đa. Biển 2 Hết Mọi Lệnh Cấm (Biển này còn ghê hơn Biển 1). Biển 3 Hết Hạn Chế Tốc Độ Tối Thiểu.",0,4,8);
        addQuestion(q194);
        Question q195 = new Question("Vạch kẻ đường nào dưới đây là vạch phân chia các làn xe cùng chiều?"," Vạch 1."," Vạch 2."," Vạch 3."," Vạch 1 và 2.",4,"q195"," Vạch màu trắng là phân chia Cùng Chiều (Cùng chiều có nhiều làn xe). Vạch màu vàng là phân chia Hai Chiều.",0,4,8);
        addQuestion(q195);
        Question q196 = new Question("Trường hợp này xe nào được quyền đi trước?"," Mô tô."," Xe con.",null,null,2,"q196"," Biển STOP là phải dừng lại.",0,5,8);
        addQuestion(q196);
        Question q197 = new Question("Xe nào đỗ vi phạm quy tắc giao thông?","Chỉ mô tô."," Chỉ xe tải."," Cả ba xe."," Chỉ mô tô và xe tải.",3,"q197"," Xe Con và Xe Mô Tô đậu xe cán vạch dành cho người đi bộ nên VI PHẠM. Xe Tải đậu xe ngược chiều di chuyển nên VI PHẠM.",0,5,8);
        addQuestion(q197);
        Question q198 = new Question("Trong hình dưới đây, xe nào chấp hành đúng quy tắc giao thông?","Chỉ xe khách, mô tô."," Tất cả các loại xe trên."," Không xe nào chấp hành đúng quy tắc giao thông",null,2,"q198","  Hướng xe từ góc ảnh bên trái phía dưới: Xe Khách dừng lại vì Đèn đang Màu Đỏ Xe Tải đi thẳng vì Đèn Xanh có mũi tên đi thẳng bắt buộc đi thẳng. Xe Con rẽ phải vì Đèn Xanh có mũi tên rẽ phải nên bắt buộc rẽ phải. 2/ Hướng xe từ góc ảnh bên phải phía trên phân tích giống vậy.",0,5,8);
        addQuestion(q198);
        Question q199 = new Question("Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?", "Xe tải, xe con.", "Xe khách, xe con."," Xe khách, xe tải.", null,3,"q199", "Mẹo gặp sa hình loại này và câu hỏi là XE NÀO VI PHẠM thì: Tìm đáp án KHÔNG CÓ XE CON chọn là chắc chắn đúng." ,0,5,8);
        addQuestion(q199);
        Question q200 = new Question("Trong tình huống dưới đây, xe đầu kéo kéo rơ moóc (xe container) đang rẽ phải, xe con màu xanh và xe máy phía sau xe container đi như thế nào để đảm bảo an toàn?"," Vượt về phía bên phải để đi tiếp.","Giảm tốc độ chờ xe container rẽ xong rồi tiếp tục đi.","Vượt về phía bên trái để đi tiếp.",null,2,"q200"," Mẹo gặp sa hình này thấy 3 chữ GIẢM TỐC ĐỘ thì chọn ngày là chắc chắn đúng. Chạy ngoài đường gặp xe Container thì giảm tốc độ và nhường nó cho lành.",0,5,8);
        addQuestion(q200);

    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();

        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NUMBER, question.getAnswerNumber());
        cv.put(QuestionsTable.COLUMN_IMG, question.getImage());
        cv.put(QuestionsTable.COLUMN_EXPLAIN, question.getExplain());
        cv.put(QuestionsTable.COLUMN_SPECIAL, question.getSpecial());
        cv.put(QuestionsTable.COLUMN_TYPE, question.getType());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategory());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    private void addCategory(Category category){
        ContentValues cv = new ContentValues();

        cv.put(CategoryTable.COLUMN_NAME, category.getName());
        db.insert(CategoryTable.TABLE_NAME,null,cv);
    }

    private void addType(Type type){
        ContentValues cv = new ContentValues();

        cv.put(TypeTable.COLUMN_NAME, type.getName());
        db.insert(TypeTable.TABLE_NAME,null,cv);
    }

    private void fillCategory(){
        Category c1 = new Category("Đề 1");
        addCategory(c1);
        Category c2 = new Category("Đề 2");
        addCategory(c2);
        Category c3 = new Category("Đề 3");
        addCategory(c3);
        Category c4 = new Category("Đề 4");
        addCategory(c4);
        Category c5 = new Category("Đề 5");
        addCategory(c5);
        Category c6 = new Category("Đề 6");
        addCategory(c6);
        Category c7 = new Category("Đề 7");
        addCategory(c7);
        Category c8 = new Category("Đề 8");
        addCategory(c8);
    }
    private void fillType() {
        Type t1 = new Type("Khái niệm và quy tắc");
        addType(t1);
        Type t2 = new Type("Văn hóa đạo đức");
        addType(t2);
        Type t3 = new Type("Kỹ thuật lái xe");
        addType(t3);
        Type t4 = new Type("Biển báo đường bộ");
        addType(t4);
        Type t5 = new Type("Sa hình");
        addType(t5);
    }

    public List<Category> getAllCategory(){
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoryTable.TABLE_NAME, null);
        if ((c.moveToFirst())){
            do{
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoryTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoryTable.COLUMN_NAME)));
                categoryList.add(category);
            }while  (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    public List<Type> getAllType(){
        List<Type> typeList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TypeTable.TABLE_NAME, null);
        if ((c.moveToFirst())){
            do{
                Type type = new Type();
                type.setId(c.getInt(c.getColumnIndex(TypeTable._ID)));
                type.setName(c.getString(c.getColumnIndex(TypeTable.COLUMN_NAME)));
                typeList.add(type);
            }while  (c.moveToNext());
        }
        c.close();
        return typeList;
    }

    public ArrayList<Question> getAllQuestions(int categoryId){
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();
        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryId)} ;


        Cursor c = db.query(QuestionsTable.TABLE_NAME, null,selection,selectionArgs,null,null,null);
        if (c.moveToFirst()) {
            do{
                Question question = new Question();
                question.setQuestionId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER)));
                question.setImage(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_IMG)));
                question.setExplain(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_EXPLAIN)));
                question.setSpecial(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_SPECIAL)));
                question.setType(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TYPE)));
                question.setCategory(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while (c.moveToNext());
        }

        c.close();
        return  questionList;
    }

    public ArrayList<Question> getAllTypicalQuestions(int typeId){
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();
        String selection = QuestionsTable.COLUMN_TYPE + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(typeId)} ;


        Cursor c = db.query(QuestionsTable.TABLE_NAME, null,selection,selectionArgs,null,null,null);
        if (c.moveToFirst()) {
            do{
                Question question = new Question();
                question.setQuestionId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER)));
                question.setImage(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_IMG)));
                question.setExplain(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_EXPLAIN)));
                question.setSpecial(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_SPECIAL)));
                question.setType(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TYPE)));
                question.setCategory(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while (c.moveToNext());
        }

        c.close();
        return  questionList;
    }

    public ArrayList<Question> getAllSpecialQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();
        String selection = QuestionsTable.COLUMN_SPECIAL + " = ?";
        String[] selectionArgs = new String[]{"1"};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestionId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER)));
                question.setImage(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_IMG)));
                question.setExplain(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_EXPLAIN)));
                question.setSpecial(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_SPECIAL)));
                question.setType(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TYPE)));
                question.setCategory(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }


    public ArrayList<Question> getAllType1() {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();
        String selection = QuestionsTable.COLUMN_TYPE + " = ?";
        String[] selectionArgs = new String[]{"1"};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestionId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER)));
                question.setImage(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_IMG)));
                question.setExplain(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_EXPLAIN)));
                question.setSpecial(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_SPECIAL)));
                question.setType(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TYPE)));
                question.setCategory(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getAllType2() {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();
        String selection = QuestionsTable.COLUMN_TYPE + " = ?";
        String[] selectionArgs = new String[]{"2"};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestionId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER)));
                question.setImage(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_IMG)));
                question.setExplain(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_EXPLAIN)));
                question.setSpecial(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_SPECIAL)));
                question.setType(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TYPE)));
                question.setCategory(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getAllType3() {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();
        String selection = QuestionsTable.COLUMN_TYPE + " = ?";
        String[] selectionArgs = new String[]{"3"};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestionId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER)));
                question.setImage(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_IMG)));
                question.setExplain(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_EXPLAIN)));
                question.setSpecial(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_SPECIAL)));
                question.setType(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TYPE)));
                question.setCategory(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getAllType4() {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();
        String selection = QuestionsTable.COLUMN_TYPE + " = ?";
        String[] selectionArgs = new String[]{"4"};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestionId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER)));
                question.setImage(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_IMG)));
                question.setExplain(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_EXPLAIN)));
                question.setSpecial(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_SPECIAL)));
                question.setType(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TYPE)));
                question.setCategory(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getAllType5() {
        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();
        String selection = QuestionsTable.COLUMN_TYPE + " = ?";
        String[] selectionArgs = new String[]{"5"};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestionId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER)));
                question.setImage(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_IMG)));
                question.setExplain(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_EXPLAIN)));
                question.setSpecial(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_SPECIAL)));
                question.setType(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TYPE)));
                question.setCategory(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
