using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model.Containers;
using Toy_Language_CS_Interpreter.Model.Statement;
using Toy_Language_CS_Interpreter.Model.Statement.FileStatement;

namespace Toy_Language_CS_Interpreter.Model
{
    class ProgramState
    {
        private Containers.IDictionary<string, int> symbolTable;
        private IStack<IStatement> executionStack;
        private Containers.IList<int> output;
        private IFileTable<int, FileData> fileTable;

        public ProgramState(Containers.IDictionary<string, int> symbolTable, IStack<IStatement> executionStack, Containers.IList<int> output, IFileTable<int, FileData> fileTable)
        {
            this.symbolTable = symbolTable;
            this.executionStack = executionStack;
            this.output = output;
            this.fileTable = fileTable;
        }


        public Containers.IDictionary<string, int> SymbolTable
        {
            get { return symbolTable; }
            set { symbolTable = value; }
        }

        public IStack<IStatement> ExecutionStack
        {
            get { return executionStack; }
            set { executionStack = value; }
        }

        public Containers.IList<int> Output
        {
            get { return output; }
            set { output = value; }
        }

        public IFileTable<int, FileData> FileTable
        {
            get { return fileTable; }
            set { fileTable = value; }
        }

        public override string ToString()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.Append("ExecutionSack: \r\n");
            stringBuilder.Append(executionStack).Append("\r\n");
            stringBuilder.Append("Symbol Table:\r\n");
            stringBuilder.Append(symbolTable).Append("\r\n");
            stringBuilder.Append("FileTable:\r\n");
            stringBuilder.Append(fileTable).Append("\r\n");
            stringBuilder.Append("Output:\r\n");
            stringBuilder.Append(output).Append("\r\n");

            return stringBuilder.ToString();
        }

    }
}
