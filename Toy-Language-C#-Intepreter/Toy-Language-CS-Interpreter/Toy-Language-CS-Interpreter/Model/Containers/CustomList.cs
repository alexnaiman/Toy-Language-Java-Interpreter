using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.Model.Containers
{
    class CustomList<T> : IList<T>
    {
        private List<T> list;

        public CustomList()
        {
            list = new List<T>();
        }

        public void Add(T element)
        {
            list.Add(element);
        }

        public T Get(int index)
        {
            return list.ElementAt(index);
        }

        public IEnumerator<T> GetEnumerator()
        {
            return list.GetEnumerator();
        }

        public void Remove(int index)
        {
            try
            {
                list.RemoveAt(index);
            }
            catch(ArgumentOutOfRangeException e)
            {
                throw new IndexOutOfRangeException("Cannot remove element at that index! Index to big or small");
            }
        }

        public int Size()
        {
            return list.Count;
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return list.GetEnumerator();
        }
        public override string ToString()
        {
            StringBuilder builder = new StringBuilder();
            list.ForEach(e => builder.Append(e.ToString()).Append("\r\n"));
            return builder.ToString();
        }
    }
}
