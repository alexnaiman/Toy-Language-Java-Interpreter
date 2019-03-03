using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model.CustomException;

namespace Toy_Language_CS_Interpreter.Model.Containers
{
    class CustomDictionary<K, V> : IDictionary<K, V>
    {
        Dictionary<K, V> dictionary;
        public CustomDictionary()
        {
            dictionary = new Dictionary<K, V>();
        }
        public IDictionary<K, V> Clone()
        {
            throw new NotImplementedException();
        }

        public V Get(K key)
        {
            if (dictionary.ContainsKey(key))
                return this.dictionary[key];
            else
                throw new NullEntryException("Key not exist in table");
        }

        public List<K> Keys()
        {
            return new List<K>(dictionary.Keys);
        }

        public void Put(K key, V value)
        {
            dictionary[key] = value;
        }

        public void Remove(K key)
        {
            dictionary.Remove(key);
        }

        public List<V> Values()
        {
            return new List<V>(dictionary.Values);
        }
        public override string ToString()
        {
            StringBuilder stringBuilder = new StringBuilder();
            Keys().ForEach(e => stringBuilder.Append(e + "-->" + Get(e)).Append("\r\n"));
            return stringBuilder.ToString();
        }
    }
}
