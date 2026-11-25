class Solution {
    public int firstUniqChar(String s) {
       int freq[]=new int[26];
       for(char ch:s.toCharArray()) {
        freq[ch-'a']++;
       }
       Queue<Integer> q=new LinkedList<>();
       for(int i=0;i<s.length();i++){
        q.offer(i);
        while(!q.isEmpty() && freq[s.charAt(q.peek())-'a']>1)q.poll();
       }
       if(q.isEmpty())return-1;
       else return q.poll();
    }
}