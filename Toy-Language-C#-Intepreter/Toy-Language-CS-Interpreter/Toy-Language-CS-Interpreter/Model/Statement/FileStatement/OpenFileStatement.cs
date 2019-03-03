using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.CustomException;

namespace Toy_Language_CS_Interpreter.Model.Statement.FileStatement
{
    class OpenFileStatement : IStatement
    {
        private string variableName;
        private string fileName;

        public OpenFileStatement(string variableName, string fileName)
        {
            this.variableName = variableName;
            this.fileName = fileName;
        }

        public ProgramState Execute(ProgramState state)
        {
            foreach (FileData fileData in state.FileTable.GetAllValues())
            {
                if (fileData.FileName == fileName)
                {
                    throw new FileException("File already opened");
                }
            }

            StreamReader reader = new StreamReader(fileName,true);
            FileData data = new FileData(fileName, reader);
            int id = IntGenerator.Generate();
            state.FileTable.Add(id, data);
            state.SymbolTable.Put(variableName, id);
            return state;
        }

        public override string ToString()
        {
            return "OpenFile(" + variableName + "," + fileName + ")";
        }
    }
}
