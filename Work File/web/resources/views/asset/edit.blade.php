@extends('app')

@section('content')


{!! Form::open(array('url' => '/assets/modify', 'class' => 'form-horizontal')) !!}

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('aidlabel','Asset ID: ')!!}
		</div>
    	<div class="col-sm-10">
      		{!!Form::text('ID',$AC->ID)!!}
      		
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('assetcodelabel','Asset Code: ')!!}
		</div>
    	<div class="col-sm-10">
      		{!!Form::text('assetcode',$AC -> assetcode)!!}
      		
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('assetcodelabel','Equipment Name: ')!!}
		</div>
    	<div class="col-sm-10">
    		@foreach($Enames as $index => $equipment)
    			@if ($AC->Ecode == $index+1)
      			{!!Form::text('Ename',$equipment->Name)!!}
      			@endif
      		@endforeach		
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('preequip','Pre Existing Equipments:')!!}
		</div>
    	<div class="col-sm-10">
      		<select id="selector" name="selector" class="form-control" multiple="multiple">
      			@foreach($Enames as $index => $equipment)
      			<option value="{{$index + 1}}">{{$equipment->Name}}</option>
      			@endforeach
      		</select>
    	</div>
	</div>

	<div class="form-group">
		<div class="col-sm-2"></div>
		<div class="col-sm-10">
			<input type="hidden" name="_token" value="{{ csrf_token() }}">
			<input type="submit" name="modify" value="Edit">
			<input type="submit" name="modify" value="Delete">
		</div>
	</div>

{!!Form::close()!!}

@endsection