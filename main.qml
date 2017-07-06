import QtQuick 2.7
import QtQuick.Controls 2.1
import QtQuick.Dialogs 1.2
import QtQuick.Controls.Material 2.1

ApplicationWindow {
    id:main_root
    visible: true
    width: 480
    height: 640
    title: qsTr("Hello")
    property int str1:0;
    property int str2:0;
    property int count: 0;
    property bool in1:false;
    property string lp1;
    property string lp2;
    property bool b_main : true;
    property var arr0 : [[0,0,0],
                [1,2,3],
                [1,4,7],
                [1,5,9],
                [2,5,8],
                [3,5,7],
                [3,6,9],
                [4,5,6],
                [7,8,9]]
    property var arr1 : [0,0,0,0,0,0,0,0,0]
    property var arr2 : [0,0,0,0,0,0,0,0,0]
    function textEdit(arr){
        var str;
        if (b_main){

            b_main = false;
            str = 'X';
            arr1[arr-1]=arr;
            for (var i1=1;i1<9;i1++){
                var arr_b1_1=false,arr_b1_2=false,arr_b1_3=false;
                for (var i2=0;i2<9;i2++){
                    if(arr0[i1][0]===arr1[i2]) arr_b1_1 = true;
                    if(arr0[i1][1]===arr1[i2]) arr_b1_2 = true;
                    if(arr0[i1][2]===arr1[i2]) arr_b1_3 = true;
                }
                if(arr_b1_1 && arr_b1_2 && arr_b1_3){
                    messageDialog.text = "Победил "+lp1;
                    str1++;
                    in1=true;
                }
            }
        } else {
            b_main = true;
            str = 'O';
            arr2[arr-1]=arr
            for (var i3=1;i3<9;i3++){
                var arr_b2_1=false,arr_b2_2=false,arr_b2_3=false;
                for (var i4=0;i4<9;i4++){
                    if(arr0[i3][0]===arr2[i4]) arr_b2_1 = true;
                    if(arr0[i3][1]===arr2[i4]) arr_b2_2 = true;
                    if(arr0[i3][2]===arr2[i4]) arr_b2_3 = true;
                }
                if(arr_b2_1 && arr_b2_2 && arr_b2_3){
                    messageDialog.text = "Победил "+lp2;
                    str2++;
                    in1=true;
                }
            }
        }
        return str;
    }
    StackView{
        id: stack
        initialItem: form1
        anchors.fill: parent
    }
    Component{
        id: form1
        Image {
            source: "i2.jpeg"
            anchors.fill: parent
            Pane{
                width: parent.width*0.8
                height: parent.width*0.9
                padding: 0
                Material.elevation: 6
                anchors.centerIn: parent
                Label{
                    text:"Sing Up"
                    font.pixelSize: parent.width*0.1
                    font.bold: true
                    anchors.horizontalCenter: parent.horizontalCenter
                    topPadding: parent.height/5
                }
                Column{
                    anchors.verticalCenter: parent.verticalCenter
                    leftPadding: parent.width/3.1
                    width: parent.width*0.9
                    TextField{
                        id:t1
                        maximumLength: 12
                        width: parent.width*0.4
                        placeholderText: "Игрок 1"
                        font.pixelSize: parent.width*0.05
                    }
                    TextField{
                        id:t2
                        maximumLength: 12
                        width: parent.width*0.4
                        placeholderText: "Игрок 2"
                        font.pixelSize: parent.width*0.05
                        anchors.margins: 50
                    }
                    Button{
                        id: root_b
                        width: parent.width*0.4
                        height: parent.height*0.3
                        text: qsTr("Log In")
                        onClicked: {
                            if ((t1.length>=1)&&(t2.length>=1)){
                            lp1 = t1.text
                            lp2 = t2.text
                            stack.pop(form1);
                            stack.push(form2);
                            }
                        }
                    }
                }
            }
        }
    }
    Component{
        id:form2
        Image {
            source: "i2.jpeg"
            Pane{
                width: parent.width/1.2
                height: parent.height/7
                anchors.horizontalCenter: parent.horizontalCenter
                anchors.top: parent.top
                anchors.topMargin: parent.width/10
                padding: 0
                Material.elevation: 6
                Rectangle{
                    anchors.fill: parent
                    color: "white"
                    Label{
                        text:lp1+" - "+str1
                        anchors.left: parent.left
                        leftPadding: 20
                        topPadding: 20
                        font.pixelSize: parent.width/20
                        anchors.top: parent.top
                        font.bold: true
                    }
                    Label{
                        text:str2+" - "+lp2
                        anchors.right: parent.right
                        rightPadding: 20
                        topPadding: 20
                        font.pixelSize: parent.width/20
                        anchors.top: parent.top
                        font.bold: true
                    }
                    Label{
                        id:lroot
                        text:"Ход "+lp1
                        anchors.horizontalCenter: parent.horizontalCenter
                        font.pixelSize: parent.width/20
                        anchors.bottom: parent.bottom
                        font.bold: true
                        bottomPadding: 20
                    }
                }
            }
            Pane{
                width: parent.width/1.2
                height: parent.width/1.2
                padding: 0
                Material.elevation: 6
                anchors.horizontalCenter: parent.horizontalCenter
                anchors.bottom: parent.bottom
                anchors.bottomMargin: 50
                Rectangle{
                    anchors.fill: parent
                    color: "white"
                    Grid{
                        id:g1
                        anchors.fill: parent
                        spacing: 5
                        columns: 3
                        rows: 3
                        function editL(){
                            if(b_main){
                                lroot.text="Ход "+lp1
                            }else{
                                lroot.text="Ход "+lp2
                            }
                        }

                        function buttonOn(){
                            for(var i=0;i<9;i++){
                                arr1[i]=0;
                                arr2[i]=0;
                            }
                            b1.enabled = true;
                            l1.text = "";
                            b2.enabled = true;
                            l2.text = "";
                            b3.enabled = true;
                            l3.text = "";
                            b4.enabled = true;
                            l4.text = "";
                            b5.enabled = true;
                            l5.text = "";
                            b6.enabled = true;
                            l6.text = "";
                            b7.enabled = true;
                            l7.text = "";
                            b8.enabled = true;
                            l8.text = "";
                            b9.enabled = true;
                            l9.text = "";
                            messageDialog.open();
                            in1=false;
                            b_main=true;
                            lroot.text="Ход "+lp1;
                            count = 0;
                        }
                        function ifHod(a){
                            if(a===9){
                                g1.buttonOn();
                                messageDialog.text="Ничья";
                                messageDialog.open();
                            }
                        }

                        Rectangle{
                            width: parent.width/3
                            height: parent.height/3
                            color:"#FAFAFA"
                            Button{
                                id: b1
                                text: ""
                                anchors.fill: parent
                                opacity: 0
                                onClicked: {
                                    l1.text = main_root.textEdit(1);
                                    b1.enabled=false;
                                    count++;
                                    if(in1)g1.buttonOn();
                                    g1.editL();
                                    g1.ifHod(count);
                                }
                            }
                            Label{
                                id: l1
                                anchors.fill: parent
                                text: ""
                                font.pixelSize: parent.width
                                horizontalAlignment: Text.AlignHCenter
                                verticalAlignment: Text.AlignVCenter
                            }
                        }
                        Rectangle{
                            width: (parent.width/3)-5
                            height: parent.height/3
                            color:"white"
                            Button{
                                id: b2
                                text: ""
                                anchors.fill: parent
                                opacity: 0
                                onClicked: {
                                    l2.text = main_root.textEdit(2);
                                    b2.enabled=false;
                                    count++;
                                    if(in1)g1.buttonOn();
                                    g1.editL();
                                    g1.ifHod(count);
                                }
                            }
                            Label{
                                id: l2
                                anchors.fill: parent
                                text: ""
                                font.pixelSize: parent.width
                                horizontalAlignment: Text.AlignHCenter
                                verticalAlignment: Text.AlignVCenter
                            }
                        }
                        Rectangle{
                            width: (parent.width/3)-5
                            height: parent.height/3
                            color:"#FAFAFA"
                            Button{
                                id:b3
                                text: ""
                                anchors.fill: parent
                                opacity: 0
                                onClicked: {
                                    l3.text = main_root.textEdit(3);
                                    b3.enabled=false;
                                    count++;
                                    if(in1)g1.buttonOn();
                                    g1.editL();
                                    g1.ifHod(count);
                                }
                            }
                            Label{
                                id: l3
                                anchors.fill: parent
                                text: ""
                                font.pixelSize: parent.width
                                verticalAlignment: Text.AlignVCenter
                                horizontalAlignment: Text.AlignHCenter
                            }
                        }
                        Rectangle{
                            width: parent.width/3
                            height: (parent.height/3)-5
                            color:"white"
                            Button{
                                id:b4
                                text: ""
                                anchors.fill: parent
                                opacity: 0
                                onClicked: {
                                    l4.text = main_root.textEdit(4);
                                    b4.enabled=false;
                                    count++;
                                    if(in1)g1.buttonOn();
                                    g1.editL();
                                    g1.ifHod(count);
                                }
                            }
                            Label{
                                id: l4
                                anchors.fill: parent
                                text: ""
                                font.pixelSize: parent.width
                                verticalAlignment: Text.AlignVCenter
                                horizontalAlignment: Text.AlignHCenter
                            }
                        }
                        Rectangle{
                            width: (parent.width/3)-5
                            height: (parent.height/3)-5
                            color:"#FAFAFA"
                            Button{
                                id:b5
                                text: ""
                                anchors.fill: parent
                                opacity: 0
                                onClicked: {
                                    l5.text = main_root.textEdit(5);
                                    b5.enabled=false;
                                    count++;
                                    if(in1)g1.buttonOn();
                                    g1.editL();
                                    g1.ifHod(count);
                                }
                            }
                            Label{
                                id: l5
                                anchors.fill: parent
                                text: ""
                                font.pixelSize: parent.width
                                verticalAlignment: Text.AlignVCenter
                                horizontalAlignment: Text.AlignHCenter
                            }
                        }
                        Rectangle{
                            width: (parent.width/3)-5
                            height: (parent.height/3)-5
                            color:"white"
                            Button{
                                id:b6
                                text: ""
                                anchors.fill: parent
                                opacity: 0
                                onClicked: {
                                    l6.text = main_root.textEdit(6);
                                    b6.enabled=false;
                                    count++;
                                    if(in1)g1.buttonOn();
                                    g1.editL();
                                    g1.ifHod(count);
                                }
                            }
                            Label{
                                id: l6
                                anchors.fill: parent
                                text: ""
                                font.pixelSize: parent.width
                                verticalAlignment: Text.AlignVCenter
                                horizontalAlignment: Text.AlignHCenter
                            }
                        }
                        Rectangle{
                            width: parent.width/3
                            height: (parent.height/3)-5
                            color:"#FAFAFA"
                            Button{
                                id:b7
                                text: ""
                                anchors.fill: parent
                                opacity: 0
                                onClicked: {
                                    l7.text = main_root.textEdit(7);
                                    b7.enabled=false;
                                    count++;
                                    if(in1)g1.buttonOn();
                                    g1.editL();
                                    g1.ifHod(count);
                                }
                            }
                            Label{
                                id: l7
                                anchors.fill: parent
                                text: ""
                                font.pixelSize: parent.width
                                verticalAlignment: Text.AlignVCenter
                                horizontalAlignment: Text.AlignHCenter
                            }
                        }
                        Rectangle{
                            width: (parent.width/3)-5
                            height: (parent.height/3)-5
                            color:"white"
                            Button{
                                id:b8
                                text: ""
                                anchors.fill: parent
                                opacity: 0
                                onClicked: {
                                    l8.text = main_root.textEdit(8);
                                    b8.enabled=false;
                                    count++;
                                    if(in1)g1.buttonOn();
                                    g1.editL();
                                    g1.ifHod(count);
                                }
                            }
                            Label{
                                id: l8
                                anchors.fill: parent
                                text: ""
                                font.pixelSize: parent.width
                                verticalAlignment: Text.AlignVCenter
                                horizontalAlignment: Text.AlignHCenter
                            }
                        }
                        Rectangle{
                            width: (parent.width/3)-5
                            height: (parent.height/3)-5
                            color:"#FAFAFA"
                            Button{
                                id:b9
                                text: ""
                                anchors.fill: parent
                                opacity: 0
                                onClicked: {
                                    l9.text = main_root.textEdit(9);
                                    b9.enabled=false;
                                    count++;
                                    if(in1)g1.buttonOn();
                                    g1.editL();
                                    g1.ifHod(count);
                                }
                            }
                            Label{
                                id: l9
                                anchors.fill: parent
                                text: ""
                                font.pixelSize: parent.width
                                verticalAlignment: Text.AlignVCenter
                                horizontalAlignment: Text.AlignHCenter
                            }
                        }
                    }
                    Rectangle{
                        color: "black"
                        width: parent.width
                        height:5
                        y:parent.width/1.5
                    }
                    Rectangle{
                        color: "black"
                        width: parent.width
                        height:5
                        y:parent.width/3
                    }
                    Rectangle{
                        color: "black"
                        width:5
                        height: parent.height
                        x:parent.height/1.5
                    }
                    Rectangle{
                        color: "black"
                        width:5
                        height: parent.height
                        x:parent.height/3
                    }
                }
            }
        }
    }
    MessageDialog {
        id: messageDialog
        title: "GG WP"
        text: "Ничья"
        onClickedButtonChanged: text = "Ничья";
    }
}
