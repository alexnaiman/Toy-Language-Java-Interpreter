using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model.CustomException;

namespace Toy_Language_CS_Interpreter.Model.Containers
{
    class FileTable<K, V> : IFileTable<K, V>
    {
        private Dictionary<K, V> dictionary;

        public FileTable()
        {
            dictionary = new Dictionary<K, V>();
        }

        public void Add(K key, V value)
        {
            dictionary[key] = value;
        }

        public bool Contains(K key)
        {
            return dictionary.ContainsKey(key);
        }

        public void Delete(K key)
        {
            dictionary.Remove(key);
        }

        public V Get(K key)
        {
            if (dictionary.ContainsKey(key))
                return this.dictionary[key];
            else
                throw new NullEntryException("Key not exist in table");
        }

        public List<K> GetAllKeys()
        {
            return new List<K>(dictionary.Keys);
        }

        public List<V> GetAllValues()
        {
            return new List<V>(dictionary.Values);
        }
        public override string ToString()
        {
            StringBuilder stringBuilder = new StringBuilder();
            GetAllKeys().ForEach(e => stringBuilder.Append(e + "-->" + Get(e)).Append("\r\n"));
            return stringBuilder.ToString();
        }
    }
}
