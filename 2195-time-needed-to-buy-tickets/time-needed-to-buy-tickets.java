class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<tickets.length;i++){
            q.add(i);
        }
       int  time=0;
while(!q.isEmpty()){
    int person=q.poll();
    tickets[person]--;
    time++;
    if(tickets[person]>0){
        q.add(person);
    }
    else if(tickets[person]==0 && person==k)return time;
}
return time;
    }
}