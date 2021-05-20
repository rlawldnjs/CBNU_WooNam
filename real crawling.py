from bs4 import BeautifulSoup
from pprint import pprint
import requests
 
html = requests.get("https://sw7up.cbnu.ac.kr/community/notice?page=1&limit=10&sort=-createdAt")
soup = BeautifulSoup(html.text, 'html.parser')
html.close()
 
data1 = soup.find('div',{'class':'main-container mb-5'})
#pprint(data1)
 
data2 = data1.findAll('div',{'class':'card card-frame py-3 px-3 px-sm-4'})
#pprint(data2)
 
title_list1 = []
for t in data2:
    title_list1.append(t.text)
#pprint(title_list1)
 
 
title_list2 = [t.text for t in data2]
#pprint(title_list2)
 
data_list = soup.findAll('div',{'class':'main-container mb-5'})
 
week_title_list = []
 
for divisionData1 in data_list:
    divisionData2 = divisionData1.findAll('div',{'class':'card card-frame py-3 px-3 px-sm-4'})
    #pprint(divisionData2)
    title_list3 = [t.text for t in divisionData2]
    #pprint(title_list3)
    #week_title_list.extend(title_list3) 1차원
    week_title_list.append(title_list3)
 
pprint(week_title_list)
