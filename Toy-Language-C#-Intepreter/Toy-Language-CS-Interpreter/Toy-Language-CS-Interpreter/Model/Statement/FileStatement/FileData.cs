using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.Model.Statement.FileStatement
{
    class FileData
    {
        private string fileName;
        private StreamReader reader;

        public FileData(string fileName, StreamReader reader)
        {
            this.fileName = fileName;
            this.reader = reader;
        }
        public string FileName
        {
            get { return fileName; }
            set { fileName = value; }
        }

        public StreamReader Reader
        {
            get { return reader; }
            set { reader = value; }
        }
        public override string ToString()
        {
            return fileName;
        }
    }
}
