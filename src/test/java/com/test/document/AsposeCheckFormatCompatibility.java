package com.test.document;

import com.aspose.words.FileFormatInfo;
import com.aspose.words.FileFormatUtil;
import com.aspose.words.LoadFormat;
import com.help.Utils;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @Auther: zch
 * @Date: 2019/1/11 11:30
 * @Description:格式化文件目录中的文档并且区域版式
 */
public class AsposeCheckFormatCompatibility {

    /**
     * 格式化文件目录中的文档并且区域版式
     * @throws Exception
     */
    @Test
    public void test()throws Exception{
        String dataDir = Utils.getDataDir(this.getClass());


        File[] fileList = new java.io.File(dataDir).listFiles();

        // Loop through all found files.
        for (File file : fileList)
        {
            if (file.isDirectory())
                continue;

            // Extract and display the file name without the path.
            String nameOnly = file.getName();
            System.out.print(nameOnly);

            // Check the file format and move the file to the appropriate folder.
            String fileName = file.getPath();
            FileFormatInfo info = FileFormatUtil.detectFileFormat(fileName);

            // Display the document type.
            switch (info.getLoadFormat())
            {
                case LoadFormat.DOC:
                    System.out.println("\tMicrosoft Word 97-2003 document.");
                    break;
                case LoadFormat.DOT:
                    System.out.println("\tMicrosoft Word 97-2003 template.");
                    break;
                case LoadFormat.DOCX:
                    System.out.println("\tOffice Open XML WordprocessingML Macro-Free Document.");
                    break;
                case LoadFormat.DOCM:
                    System.out.println("\tOffice Open XML WordprocessingML Macro-Enabled Document.");
                    break;
                case LoadFormat.DOTX:
                    System.out.println("\tOffice Open XML WordprocessingML Macro-Free Template.");
                    break;
                case LoadFormat.DOTM:
                    System.out.println("\tOffice Open XML WordprocessingML Macro-Enabled Template.");
                    break;
                case LoadFormat.FLAT_OPC:
                    System.out.println("\tFlat OPC document.");
                    break;
                case LoadFormat.RTF:
                    System.out.println("\tRTF format.");
                    break;
                case LoadFormat.WORD_ML:
                    System.out.println("\tMicrosoft Word 2003 WordprocessingML format.");
                    break;
                case LoadFormat.HTML:
                    System.out.println("\tHTML format.");
                    break;
                case LoadFormat.MHTML:
                    System.out.println("\tMHTML (Web archive) format.");
                    break;
                case LoadFormat.ODT:
                    System.out.println("\tOpenDocument Text.");
                    break;
                case LoadFormat.OTT:
                    System.out.println("\tOpenDocument Text Template.");
                    break;
//                case LoadFormat.DOC_PRE_WORD_97: //低版本
//                    System.out.println("\tMS Word 6 or Word 95 format.");
//                    break;
                case LoadFormat.UNKNOWN:
                default:
                    System.out.println("\tUnknown format.");
                    break;
            }
        }
        System.out.println("\nProcess Completed Successfully");
    }

}
